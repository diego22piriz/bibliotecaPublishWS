package logica;

import interfaces.IControlador;
import excepciones.UsuarioRepetidoException;
import excepciones.PrestamoDuplicadoException;
import datatypes.DtBibliotecario;
import datatypes.DtLector;
import datatypes.DtLibro;
import datatypes.DtPrestamo;
import datatypes.DtUsuario;
import datatypes.DtArticulo;
import persistencia.Conexion;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.ArrayList;

public class Controlador implements IControlador {
    
    private ManejadorUsuario manejadorUsuario;
    private ManejadorMaterial manejadorMaterial;
    
    public Controlador() {
        this.manejadorUsuario = ManejadorUsuario.getInstancia();
        this.manejadorMaterial = ManejadorMaterial.getInstancia();
    }

    // Métodos específicos requeridos por la UI (usuarios)
    public void agregarLector(DtLector dtLector) throws UsuarioRepetidoException {
        if (manejadorUsuario.existeUsuario(dtLector.getCorreo())) {
            throw new UsuarioRepetidoException("Ya existe un usuario con el correo: " + dtLector.getCorreo());
        }
        manejadorUsuario.agregarLector(dtLector);
    }

    public void agregarBibliotecario(DtBibliotecario dtBibliotecario) throws UsuarioRepetidoException {
        if (manejadorUsuario.existeUsuario(dtBibliotecario.getCorreo())) {
            throw new UsuarioRepetidoException("Ya existe un usuario con el correo: " + dtBibliotecario.getCorreo());
        }
        manejadorUsuario.agregarBibliotecario(dtBibliotecario);
    }

    public void suspenderUsuario(String correo) {
        if (manejadorUsuario.existeUsuario(correo)) {
            if (manejadorUsuario.getUsuario(correo).tipoUsuario().equals("Lector")) {
                manejadorUsuario.suspenderUsuario(correo); // ahora alterna activo/inactivo
            }
        }
    }

    public void cambiarZona(String correo, String zona){
        if (manejadorUsuario.existeUsuario(correo)) {
            if (manejadorUsuario.getUsuario(correo).tipoUsuario().equals("Lector")) {
                manejadorUsuario.cambiarZona(correo, zona);
            }
        }
    }


    // Métodos para materiales
    public void agregarLibro(DtLibro dtLibro) {
        manejadorMaterial.agregarLibro(dtLibro);
    }

    public void agregarArticulo(DtArticulo dtArticulo) {
        manejadorMaterial.agregarArticulo(dtArticulo);
    }

    public void agregarPrestamo(DtPrestamo dtPrestamo) throws PrestamoDuplicadoException {
        ManejadorUsuario mUsuario = ManejadorUsuario.getInstancia();
        ManejadorMaterial mMat = ManejadorMaterial.getInstancia();

        System.out.println("[DEBUG] Iniciando agregarPrestamo:");
        System.out.println("  - Lector: " + dtPrestamo.getLectorCorreo());
        System.out.println("  - Bibliotecario: " + dtPrestamo.getBibliotecarioCorreo());
        System.out.println("  - Material ID: " + dtPrestamo.getMaterialId());
        System.out.println("  - Estado: " + dtPrestamo.getEstado());
        System.out.println("  - Fecha Solicitud: " + (dtPrestamo.getFechaSolicitud() != null ? 
            dtPrestamo.getFechaSolicitud().getDia() + "/" + dtPrestamo.getFechaSolicitud().getMes() + "/" + dtPrestamo.getFechaSolicitud().getAnio() : "null"));
        System.out.println("  - Fecha Devolución: " + (dtPrestamo.getFechaDevolucion() != null ? 
            dtPrestamo.getFechaDevolucion().getDia() + "/" + dtPrestamo.getFechaDevolucion().getMes() + "/" + dtPrestamo.getFechaDevolucion().getAnio() : "null"));

        EntityManager em = Conexion.getInstancia().getEntityManager();

        Lector lector = mUsuario.buscarLector(dtPrestamo.getLectorCorreo());
        if (lector == null) {
            throw new IllegalArgumentException("Lector no encontrado: " + dtPrestamo.getLectorCorreo());
        }

        Bibliotecario bibliotecario = mUsuario.buscarBibliotecario(dtPrestamo.getBibliotecarioCorreo());
        if (bibliotecario == null) {
            throw new IllegalArgumentException("Bibliotecario no encontrado: " + dtPrestamo.getBibliotecarioCorreo());
        }

        Material material = mMat.buscarMaterial(dtPrestamo.getMaterialId());
        if (material == null) {
            throw new IllegalArgumentException("Material no encontrado: id=" + dtPrestamo.getMaterialId());
        }

        // Verificar si ya existe un préstamo con la misma combinación
        javax.persistence.Query query = em.createQuery(
            "SELECT p FROM Prestamo p WHERE p.material.id = :materialId AND p.lector.correo = :lectorCorreo AND p.bibliotecario.correo = :bibliotecarioCorreo"
        );
        query.setParameter("materialId", dtPrestamo.getMaterialId());
        query.setParameter("lectorCorreo", dtPrestamo.getLectorCorreo());
        query.setParameter("bibliotecarioCorreo", dtPrestamo.getBibliotecarioCorreo());
        
        if (!query.getResultList().isEmpty()) {
            throw new PrestamoDuplicadoException("Ya existe un préstamo para este material, lector y bibliotecario");
        }

        em.getTransaction().begin();
        try {
            // Re-obtener las entidades en el contexto de persistencia actual
            Lector lectorManaged = em.find(Lector.class, lector.getCorreo());
            Bibliotecario bibliotecarioManaged = em.find(Bibliotecario.class, bibliotecario.getCorreo());
            Material materialManaged = em.find(Material.class, material.getId());
            
            if (lectorManaged == null || bibliotecarioManaged == null || materialManaged == null) {
                throw new IllegalArgumentException("No se pudieron cargar las entidades relacionadas");
            }
            
            // Crear el préstamo
            Prestamo prestamo = ManejadorPrestamo.getInstancia().CrearPrestamo(dtPrestamo);
            
            // Establecer la clave compuesta (OBLIGATORIO para entidades con clave compuesta)
            prestamo.setLectorCorreo(lectorManaged.getCorreo());
            prestamo.setBibliotecarioCorreo(bibliotecarioManaged.getCorreo());
            prestamo.setMaterialId(materialManaged.getId());
            
            // Establecer las relaciones con entidades gestionadas
            prestamo.setLector(lectorManaged);
            prestamo.setBibliotecario(bibliotecarioManaged);
            prestamo.setMaterial(materialManaged);
            
            System.out.println("[DEBUG] Antes de persist - Prestamo:");
            System.out.println("  - Clave: " + prestamo.getLectorCorreo() + "/" + prestamo.getBibliotecarioCorreo() + "/" + prestamo.getMaterialId());
            System.out.println("  - Estado: " + prestamo.getEstado());
            
            // Persistir el préstamo (no es necesario agregar a las colecciones, JPA lo hace automáticamente)
            em.persist(prestamo);
            System.out.println("[DEBUG] Después de persist, antes de commit");
            em.flush(); // Forzar la sincronización con la BD para ver errores específicos
            System.out.println("[DEBUG] Después de flush");
            em.getTransaction().commit();
            System.out.println("[DEBUG] Commit exitoso");
        } catch (Exception e) {
            System.err.println("[ERROR] Error durante la transacción: " + e.getClass().getName());
            System.err.println("[ERROR] Mensaje: " + e.getMessage());
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al registrar préstamo: " + e.getMessage(), e);
        }
    }
    
    // Métodos para obtener listas de datos
    public List<String> listarLectores() {
        return manejadorUsuario.listarLectores();
    }
    
    public List<String> listarLectoresSuspendidos() {
        return manejadorUsuario.listarLectoresSuspendidos();
    }
    
    public List<String> listarBibliotecarios() {
        return manejadorUsuario.listarBibliotecarios();
    }
    
    public ArrayList<String> listarMateriales() {
        return manejadorMaterial.listarMateriales();
    }
    
    public ArrayList<String> listarIdsMateriales() {
        return manejadorMaterial.listarIdsMateriales();
    }
    
    // Métodos para gestión de préstamos
    public List<Prestamo> listarPrestamos() {
        return ManejadorPrestamo.getInstancia().listarPrestamos();
    }
    
    public List<Prestamo> listarPrestamosLector(String correo) {
        return ManejadorPrestamo.getInstancia().listarPrestamosLector(correo);
    }

    public List<Prestamo> listarPrestamosBibliotecario(String correo) {
        return ManejadorPrestamo.getInstancia().listarPrestamosBibliotecario(correo);
    }

    public Prestamo buscarPrestamo(String lectorCorreo, String bibliotecarioCorreo, Long materialId) {
        return ManejadorPrestamo.getInstancia().buscarPrestamo(lectorCorreo, bibliotecarioCorreo, materialId);
    }
    
    public void actualizarPrestamo(DtPrestamo dtPrestamo) {
        EntityManager em = Conexion.getInstancia().getEntityManager();
        
        // Buscar el préstamo existente
        Prestamo prestamo = ManejadorPrestamo.getInstancia().buscarPrestamo(
            dtPrestamo.getLectorCorreo(), 
            dtPrestamo.getBibliotecarioCorreo(), 
            dtPrestamo.getMaterialId()
        );
        
        if (prestamo == null) {
            throw new IllegalArgumentException("Préstamo no encontrado");
        }
        
        em.getTransaction().begin();
        try {
            // Actualizar el préstamo
            ManejadorPrestamo.getInstancia().actualizarPrestamo(prestamo, dtPrestamo);
            
            // Hacer merge para sincronizar los cambios con la base de datos
            em.merge(prestamo);
            
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar préstamo: " + e.getMessage(), e);
        }
    }
    
    // Método para obtener materiales con muchos préstamos pendientes
    public List<String> obtenerMaterialesConPrestamosPendientes() {
        List<Object[]> resultados = manejadorMaterial.obtenerMaterialesConPrestamosPendientes();
        List<String> materialesInfo = new ArrayList<>();
        
        for (Object[] resultado : resultados) {
            Long materialId = (Long) resultado[0];
            Long cantidadPrestamos = (Long) resultado[1];
            
            // Buscar el material completo por ID
            Material material = manejadorMaterial.buscarMaterial(materialId);
            if (material == null) {
                continue; // Saltar si no se encuentra el material
            }
            
            String tipoMaterial = material instanceof Libro ? "LIBRO" : "ARTÍCULO";
            String nombreMaterial;
            
            if (material instanceof Libro) {
                nombreMaterial = ((Libro) material).getTitulo();
            } else {
                nombreMaterial = ((Articulo) material).getDescripcion();
            }
            
            String info = String.format("ID: %d | Tipo: %s | Nombre: %s | Préstamos Pendientes: %d",
                material.getId(), tipoMaterial, nombreMaterial, cantidadPrestamos);
            materialesInfo.add(info);
        }
        
        return materialesInfo;
    }
    
    // Método para obtener materiales registrados en un rango de fechas
    public List<String> obtenerMaterialesPorRangoFechas(datatypes.DtFecha fechaInicio, datatypes.DtFecha fechaFin) {
        List<Material> materiales = manejadorMaterial.obtenerMaterialesPorRangoFechas(fechaInicio, fechaFin);
        List<String> materialesInfo = new ArrayList<>();
        
        for (Material material : materiales) {
            String tipoMaterial = material instanceof Libro ? "LIBRO" : "ARTÍCULO";
            String nombreMaterial;
            
            if (material instanceof Libro) {
                nombreMaterial = ((Libro) material).getTitulo();
            } else {
                nombreMaterial = ((Articulo) material).getDescripcion();
            }
            
            String fechaIngreso = String.format("%02d/%02d/%04d", 
                material.getFechaIngreso().getDay(),
                material.getFechaIngreso().getMonth(),
                material.getFechaIngreso().getYear());
            
            String info = String.format("ID: %d | Tipo: %s | Nombre: %s | Fecha Ingreso: %s",
                material.getId(), tipoMaterial, nombreMaterial, fechaIngreso);
            materialesInfo.add(info);
        }
        
        return materialesInfo;
    }
    
    // Métodos para consultas por zona
    public List<String> obtenerTodasLasZonas() {
        return ManejadorPrestamo.getInstancia().obtenerTodasLasZonas();
    }
    
    public List<Prestamo> obtenerPrestamosDeZona(String zona) {
        return ManejadorPrestamo.getInstancia().obtenerPrestamosDeZona(zona);
    }

    public DtUsuario login(String correo, String password) {
        return ManejadorUsuario.getInstancia().login(correo, password);
    }
    
    public void cambiarEstadoPrestamo(String lectorCorreo, String bibliotecarioCorreo, Long materialId, datatypes.EstadoPrestamo nuevoEstado) {
        ManejadorPrestamo.getInstancia().cambiarEstadoPrestamo(lectorCorreo, bibliotecarioCorreo, materialId, nuevoEstado);
    }
    
    public List<String> listarPrestamosString() {
        List<Prestamo> prestamos = ManejadorPrestamo.getInstancia().listarPrestamos();
        List<String> prestamosString = new ArrayList<>();
        
        for (Prestamo prestamo : prestamos) {
            String info = String.format("Préstamo - Lector: %s, Bibliotecario: %s, Material: %d, Estado: %s", 
                prestamo.getLectorCorreo(), 
                prestamo.getBibliotecarioCorreo(), 
                prestamo.getMaterialId(), 
                prestamo.getEstado().toString());
            prestamosString.add(info);
        }
        
        return prestamosString;
    }
}


