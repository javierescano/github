//Younes El Badadi

package peliculas3ev_YEB;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_URL = "jdbc:sqlite:exam3ev_peliculas.db";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ ===");
            System.out.println("1. Dar de alta una película");
            System.out.println("2. Eliminar una película");
            System.out.println("3. Modificar una película");
            System.out.println("4. Listar todas las películas");
            System.out.println("5. Buscar película por título");
            System.out.println("6. Mostrar películas > 100 min");
            System.out.println("7. Mostrar películas antes del año 2000");
            System.out.println("8. Mostrar películas < 10€");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1 -> altaPelicula(sc);
                case 2 -> eliminarPelicula(sc);
                case 3 -> modificarPelicula(sc);
                case 4 -> listarPeliculas();
                case 5 -> buscarPorTitulo(sc);
                case 6 -> mostrarDuracionMayor100();
                case 7 -> mostrarAntes2000();
                case 8 -> mostrarPrecioMenor10();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    static Connection conectar() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    static void altaPelicula(Scanner sc) {
        try (Connection conn = conectar()) {
            System.out.print("Título: ");
            String titulo = sc.nextLine();
            System.out.print("Director: ");
            String director = sc.nextLine();
            System.out.print("Año: ");
            int anio = sc.nextInt();
            System.out.print("Duración (minutos): ");
            int duracion = sc.nextInt();
            System.out.print("Precio (€): ");
            double precio = sc.nextDouble();

            String sql = "INSERT INTO peliculas(titulo, director, anio, duracion, precio) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, titulo);
            stmt.setString(2, director);
            stmt.setInt(3, anio);
            stmt.setInt(4, duracion);
            stmt.setDouble(5, precio);
            stmt.executeUpdate();

            System.out.println("Película añadida correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    static void eliminarPelicula(Scanner sc) {
        try (Connection conn = conectar()) {
            System.out.print("ID de la película a eliminar: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM peliculas WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();

            if (filas > 0) System.out.println("Película eliminada.");
            else System.out.println("Película no encontrada.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    static void modificarPelicula(Scanner sc) {
        try (Connection conn = conectar()) {
            System.out.print("ID de la película a modificar: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Nuevo título: ");
            String titulo = sc.nextLine();
            System.out.print("Nuevo director: ");
            String director = sc.nextLine();
            System.out.print("Nuevo año: ");
            int anio = sc.nextInt();
            System.out.print("Nueva duración: ");
            int duracion = sc.nextInt();
            System.out.print("Nuevo precio: ");
            double precio = sc.nextDouble();

            String sql = "UPDATE peliculas SET titulo=?, director=?, anio=?, duracion=?, precio=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, titulo);
            stmt.setString(2, director);
            stmt.setInt(3, anio);
            stmt.setInt(4, duracion);
            stmt.setDouble(5, precio);
            stmt.setInt(6, id);
            int filas = stmt.executeUpdate();

            if (filas > 0) System.out.println("Película modificada.");
            else System.out.println("Película no encontrada.");
        } catch (SQLException e) {
            System.out.println("Error al modificar: " + e.getMessage());
        }
    }

    static void listarPeliculas() {
        try (Connection conn = conectar()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM peliculas");

            while (rs.next()) {
                mostrarFila(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
    }

    static void buscarPorTitulo(Scanner sc) {
        try (Connection conn = conectar()) {
            System.out.print("Título a buscar: ");
            String titulo = sc.nextLine();

            String sql = "SELECT * FROM peliculas WHERE titulo LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + titulo + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                mostrarFila(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
    }

    static void mostrarDuracionMayor100() {
        try (Connection conn = conectar()) {
            String sql = "SELECT * FROM peliculas WHERE duracion > 100";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                mostrarFila(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar duración: " + e.getMessage());
        }
    }

    static void mostrarAntes2000() {
        try (Connection conn = conectar()) {
            String sql = "SELECT * FROM peliculas WHERE anio < 2000";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                mostrarFila(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar año: " + e.getMessage());
        }
    }

    static void mostrarPrecioMenor10() {
        try (Connection conn = conectar()) {
            String sql = "SELECT * FROM peliculas WHERE precio < 10";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                mostrarFila(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar precio: " + e.getMessage());
        }
    }

    static void mostrarFila(ResultSet rs) throws SQLException {
        System.out.printf("ID: %d | Título: %s | Director: %s | Año: %d | Duración: %d mins | Precio: %.2f€%n",
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("director"),
                rs.getInt("anio"),
                rs.getInt("duracion"),
                rs.getDouble("precio"));
    }
}