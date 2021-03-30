package com.endava.core.repositories;

import com.endava.core.VehiclesRepository;
import com.endava.models.courses.impl.CourseImpl;
import com.endava.models.courses.impl.RentImpl;
import com.endava.models.courses.Course;
import com.endava.models.courses.Rent;
import com.endava.models.enums.VehicleType;
import com.endava.models.parkings.impl.ParkingBase;
import com.endava.models.parkings.impl.ParkingTicketImpl;
import com.endava.models.parkings.Parking;
import com.endava.models.parkings.ParkingTicket;
import com.endava.models.vehicles.impl.AirplaneImpl;
import com.endava.models.vehicles.impl.CarImpl;
import com.endava.models.vehicles.impl.ShipImpl;
import com.endava.models.vehicles.impl.TrainImpl;
import com.endava.models.vehicles.Airplane;
import com.endava.models.vehicles.Train;
import com.endava.models.vehicles.Vehicle;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import static com.endava.commands.constants.Constants.*;

public class VehiclesRepositorySQLImpl implements VehiclesRepository {

    private String dbUrl = "jdbc:mysql://localhost:3306/vehicles";
    private String dbUsername = "vehicles_user";
    private String dbPassword = "vehicles_user";

    private final List<Vehicle> vehicles = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();
    private final List<Rent> rents = new ArrayList<>();
    private final Set<Parking> parkings = new HashSet<>();
    private final Set<ParkingTicket> parkingTickets = new HashSet<>();


    @Override
    public List<Vehicle> getVehicles() {
        String query = "SELECT vehicle_type_id, registration_number, load_capacity, price_per_km,charter, carts FROM vehicles";
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(query)) {
                try (ResultSet result = statement.executeQuery()) {
                    return mapResultSetToVehicle(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Vehicles printed.");
    }

    @Override
    public List<Course> getCourses() {
        String query = "SELECT start_location, destination, distance, vehicle_id FROM courses";
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(query)) {
                try (ResultSet result = statement.executeQuery()) {
                    return mapResultSetToCourse(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Unable to retrieve courses.");
    }

    @Override
    public List<Rent> getRents() {
        String query = "SELECT course_id, additional_costs FROM rents";
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(query)) {
                try (ResultSet result = statement.executeQuery()) {
                    return mapResultSetToRent(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Unable to retrieve rents.");
    }

    @Override
    public Set<Parking> getParkings() {
        String query = "SELECT vehicle_type_id, name, capacity, hourly_rate FROM parkings";
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(query)) {
                try (ResultSet result = statement.executeQuery()) {
                    return mapResultSetToParking(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Unable to retrieve parkings.");
    }

    @Override
    public Set<ParkingTicket> getParkingTickets() {
        String query = "SELECT vehicle_id, parking_id, entrance_time, exit_time FROM parking_tickets";
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(query)) {
                try (ResultSet result = statement.executeQuery()) {
                    return mapResultSetToParkingTicket(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Unable to retrieve parking tickets.");
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles" +
                "(vehicle_type_id, registration_number, load_capacity, price_per_km) " +
                "values(?,?,?,?)";
        int i = 0;
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setInt(1, findTypeIdByVehicleType(vehicle.getType()));
                statement.setString(2, vehicle.getRegistrationNumber());
                statement.setInt(3, vehicle.getLoadCapacity());
                statement.setDouble(4, vehicle.getPricePerKilometer());
                i = statement.executeUpdate();
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i + " records inserted");
    }

    @Override
    public void addPlane(Airplane vehicle) {
        String sql = "INSERT INTO vehicles" +
                "(vehicle_type_id, registration_number, load_capacity, price_per_km, charter) " +
                "values(?,?,?,?,?)";
        int i = 0;
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setInt(1, findTypeIdByVehicleType(vehicle.getType()));
                statement.setString(2, vehicle.getRegistrationNumber());
                statement.setInt(3, vehicle.getLoadCapacity());
                statement.setDouble(4, vehicle.getPricePerKilometer());
                statement.setBoolean(5, vehicle.isCharter());
                i = statement.executeUpdate();
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i + " record inserted.");
    }

    @Override
    public void addTrain(Train vehicle) {
        String sql = "INSERT INTO vehicles" +
                "(vehicle_type_id, registration_number, load_capacity, price_per_km, carts) " +
                "values(?,?,?,?,?)";
        int i = 0;
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setInt(1, findTypeIdByVehicleType(vehicle.getType()));
                statement.setString(2, vehicle.getRegistrationNumber());
                statement.setInt(3, vehicle.getLoadCapacity());
                statement.setDouble(4, vehicle.getPricePerKilometer());
                statement.setInt(5, vehicle.getCarts());
                i = statement.executeUpdate();
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i + " record inserted.");
    }

    @Override
    public void addCourse(Course course) {
        String sql = "INSERT INTO courses" +
                "(vehicle_id, start_location, destination, distance) " +
                "values(?,?,?,?)";
        int i = 0;
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setInt(1, findIdByVehicle(course.getVehicle()));
                statement.setString(2, course.getStartLocation());
                statement.setString(3, course.getDestination());
                statement.setInt(4, course.getDistance());
                i = statement.executeUpdate();
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i + " record inserted.");
    }

    @Override
    public void addRent(Rent rent) {
        String sql = "INSERT INTO rents" +
                "(course_id, additional_costs) " +
                "values(?,?)";
        int i = 0;
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setInt(1, findIdByCourse(rent.getCourse()));
                statement.setDouble(2, rent.getAdditionalCosts());
                i = statement.executeUpdate();
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i + " record inserted.");
    }

    @Override
    public void addParking(Parking parking) {
        String sql = "INSERT INTO parkings" +
                "(vehicle_type_id, name, capacity, hourly_rate) " +
                "values(?,?,?,?)";
        int i = 0;
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setInt(1, findTypeIdByVehicleType(parking.getParkingType()));
                statement.setString(2, parking.getName());
                statement.setInt(3, parking.getCapacity());
                statement.setDouble(4, parking.getHourlyRate());
                i = statement.executeUpdate();
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i + " record inserted.");
    }

    @Override
    public void addParkingTicket(ParkingTicket ticket) {
        String sql = "INSERT INTO parking_tickets" +
                "(vehicle_id, parking_id, entrance_time, exit_time) " +
                "values(?,?,?,?)";
        int i = 0;
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setInt(1, findIdByVehicle(ticket.getVehicle()));
                statement.setInt(2, findIdByParking(ticket.getParking()));
                statement.setDate(3, Date.valueOf(ticket.getEntranceTime().toLocalDate()));
                statement.setDate(4, Date.valueOf(ticket.getExitTime().toLocalDate()));
                i = statement.executeUpdate();
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i + " record inserted.");
    }

    @Override
    public void removeParkingTicket(ParkingTicket ticket) {
        this.parkingTickets.remove(ticket);
    }

    @Override
    public Vehicle findVehicleById(int vehicleId) {
        String query = String.format("SELECT vehicle_type_id, registration_number, load_capacity, price_per_km, charter, carts" +
                " FROM vehicles WHERE vehicles.vehicle_id = \'%d\';", vehicleId);
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    return mapResultSetToVehicle(resultSet).get(0);
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException(String.format("No vehicle with ID %s", vehicleId));
    }

    private int findIdByVehicle(Vehicle vehicle) {
        String vehicleNumber = '\'' + vehicle.getRegistrationNumber() + '\'';
        String query = String.format(
                "SELECT vehicle_id FROM vehicles WHERE vehicles.registration_number = %s;", vehicleNumber);
        int vehicleId = -1;
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        vehicleId = resultSet.getInt("vehicle_id");
                    }
                    return vehicleId;
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException(String.format("No vehicle %s", vehicle.getRegistrationNumber()));
    }

    private VehicleType findVehicleTypeById(int vehicleTypeId) {
        String query = String.format(
                "SELECT vehicle_type FROM vehicle_types WHERE vehicle_types.vehicle_type_id = %d;", vehicleTypeId);
        VehicleType type = null;
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        type = VehicleType.valueOf(resultSet.getString("vehicle_type"));
                    }
                    return type;
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException(String.format("No vehicle type with ID %s", vehicleTypeId));
    }

    private int findTypeIdByVehicleType(VehicleType vehicleType) {
        String type = '\'' + vehicleType.toString() + '\'';
        String query = String.format(
                "SELECT vehicle_type_id FROM vehicle_types WHERE vehicle_types.vehicle_type = %s;", type);
        int typeId = 0;
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        typeId = resultSet.getInt("vehicle_type_id");
                    }
                    return typeId;
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException(String.format("No vehicle type %s", vehicleType));
    }

    @Override
    public Course findCourseById(int courseId) {
        String query = String.format("SELECT vehicle_id, start_location, destination, distance" +
                " FROM courses WHERE courses.course_id = \'%d\';", courseId);
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    return mapResultSetToCourse(resultSet).get(0);
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException(String.format("No course with ID %s", courseId));
    }

    private int findIdByCourse(Course course) {
        int vehicle = findIdByVehicle(course.getVehicle());
        int distance = course.getDistance();
        String destination = '\'' + course.getDestination() + '\'';
        String startLocation = '\'' + course.getStartLocation() + '\'';
        String query = String.format(
                "SELECT course_id FROM courses WHERE courses.vehicle_id = \'%d\' AND " +
                        "courses.distance =  \'%d\' AND " +
                        "courses.start_location = %s AND " +
                        "courses.destination =  %s;", vehicle, distance, startLocation, destination);
        int courseId = -1;
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        courseId = resultSet.getInt("course_id");
                    }
                    return courseId;
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException(String.format("No vehicle %s", courseId));
    }

    public Parking findParkingById(int parkingId) {
        String query = String.format("SELECT vehicle_type_id, name, capacity, hourly_rate" +
                " FROM parkings WHERE parking.parking_id = \'%d\';", parkingId);
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    return mapResultSetToParking(resultSet).iterator().next();
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException(String.format("No vehicle with ID %s", parkingId));
    }

    private int findIdByParking(Parking parking) {
        String parkingName = '\'' + parking.getName() + '\'';
        String query = String.format(
                "SELECT parking_id FROM parkings WHERE parkings.name = %s;", parkingName);
        int parkingId = -1;
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        parkingId = resultSet.getInt("parking_id");
                    }
                    return parkingId;
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException(String.format("No parking %s", parking.getName()));
    }

    public ParkingTicket findParkingTicketById(int parkingTicketId) {
        String query = String.format("SELECT vehicle_id, parking_id, entrance_time, exit_time" +
                " FROM parking_tickets WHERE parking_tickets.parking_ticket_id = \'%d\';", parkingTicketId);
        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            try (PreparedStatement statement = con.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    return mapResultSetToParkingTicket(resultSet).iterator().next();
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException(String.format("No ticket with ID %s",parkingTicketId));
    }


    @Override
    public Boolean vehicleExists(String vehicleNumber) {
        return vehicles.stream()
                .anyMatch(veh -> vehicleNumber.equals(veh.getRegistrationNumber()));
    }

    @Override
    public Vehicle findVehicleByNumber(String vehicleNumber) {
        return vehicles.stream()
                .filter(veh -> vehicleNumber.equals(veh.getRegistrationNumber()))
                .findAny()
                .orElseThrow(() ->
                        new NoSuchElementException(String.format(VEHICLE_NOT_EXIST_MESSAGE, vehicleNumber)));
    }


    @Override
    public Parking findParkingByName(String parkingName) {
        Parking parking = parkings.stream()
                .filter(park -> parkingName.equals(park.getName()))
                .findAny()
                .orElseThrow(() ->
                        new NoSuchElementException(String.format(PARKING_NOT_EXIST_MESSAGE, parkingName)));
        if (parking.getFreeSpaces() - 1 == 0) {
            throw new IllegalArgumentException(PARKING_NO_SPACES_MESSAGE);
        }
        return parking;
    }

    @Override
    public ParkingTicket findTicketByVehicleNumber(String vehicleNumber) {
        return parkingTickets.stream()
                .filter(tick -> vehicleNumber.equals(tick.getVehicle().getRegistrationNumber()))
                .findAny()
                .orElseThrow(() ->
                        new NoSuchElementException(
                                String.format(PARKING_TICKET_NOT_EXIST_MESSAGE, vehicleNumber)));
    }

    @Override
    public boolean vehicleHasParkingTicket(String vehicleNumber) {
        return parkingTickets.stream()
                .anyMatch(tick -> tick.getVehicle().getRegistrationNumber().equals(vehicleNumber));
    }

    @Override
    public List<Vehicle> filterByType(VehicleType vehicleType) {
        List<Vehicle> vehiclesList = new ArrayList<>(getVehicles());
        return vehiclesList.stream()
                .filter(vehicle -> vehicle.getType() == vehicleType)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> sortedByNumber(List<Vehicle> list) {
        List<Vehicle> vehiclesList = new ArrayList<>(list);
        return vehiclesList.stream()
                .sorted(
                        (v1, v2) -> v1.getRegistrationNumber().compareToIgnoreCase(v2.getRegistrationNumber()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> sortedByLoadCapacity() {
        List<Vehicle> vehiclesList = new ArrayList<>(vehicles);
        return vehiclesList.stream()
                .sorted(Comparator.comparingInt(Vehicle::getLoadCapacity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> sortedByPricePerKm() {
        List<Vehicle> vehiclesList = new ArrayList<>(vehicles);
        return vehiclesList.stream()
                .sorted(Comparator.comparingDouble(Vehicle::getPricePerKilometer))
                .collect(Collectors.toList());
    }

    private List<Vehicle> mapResultSetToVehicle(ResultSet result) throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        while (result.next()) {
            Vehicle newVehicle = null;
            VehicleType type = findVehicleTypeById(result.getInt("vehicle_type_id"));
            String registrationNumber = result.getString("registration_number");
            int loadCapacity = result.getInt("load_capacity");
            double pricePerKm = result.getDouble("price_per_km");
            boolean charter = result.getBoolean("charter");
            int carts = result.getInt("carts");
            switch (type) {
                case CAR:
                case BUS:
                    newVehicle = new CarImpl(registrationNumber, loadCapacity, pricePerKm);
                    //                            result.getString("vehicle_type"));
                    break;
                case TRAIN:
                    newVehicle = new TrainImpl(registrationNumber, loadCapacity, pricePerKm, carts);
                    break;
                case PLANE:
                    newVehicle = new AirplaneImpl(registrationNumber, loadCapacity, pricePerKm, charter);
                    break;
                case SHIP:
                    newVehicle = new ShipImpl(registrationNumber, loadCapacity, pricePerKm);
                    break;
            }
            vehicles.add(newVehicle);
        }
        return vehicles;
    }

    private List<Course> mapResultSetToCourse(ResultSet resultSet) throws SQLException {
        List<Course> courses = new ArrayList<>();
        while (resultSet.next()) {
            Course newCourse = new CourseImpl(
                    resultSet.getString("start_location"),
                    resultSet.getString("destination"),
                    resultSet.getInt("distance"),
                    findVehicleById(resultSet.getInt("vehicle_id")));
            courses.add(newCourse);
        }
        return courses;
    }

    private List<Rent> mapResultSetToRent(ResultSet resultSet) throws SQLException {
        List<Rent> courses = new ArrayList<>();
        while (resultSet.next()) {
            Rent newRent = new RentImpl(
                    findCourseById(resultSet.getInt("course_id")),
                    resultSet.getDouble("additional_costs"));
            courses.add(newRent);
        }
        return courses;
    }

    private Set<Parking> mapResultSetToParking(ResultSet resultSet) throws SQLException {
        Set<Parking> parkings = new HashSet<>();
        while (resultSet.next()) {
            Parking newParking = new ParkingBase(
                    resultSet.getString("name"),
                    resultSet.getInt("capacity"),
                    resultSet.getDouble("hourly_rate"),
                    findVehicleTypeById(resultSet.getInt("vehicle_type_id")));
            parkings.add(newParking);
        }
        return parkings;
    }

    private Set<ParkingTicket> mapResultSetToParkingTicket(ResultSet resultSet) throws SQLException {
        Set<ParkingTicket> parkingTickets = new HashSet<>();
        while (resultSet.next()) {
            ParkingTicket newParking = new ParkingTicketImpl(
                    findVehicleById(resultSet.getInt("vehicle_id")),
                    findParkingById(resultSet.getInt("parking_id")));
            parkingTickets.add(newParking);
        }
        return parkingTickets;
    }

//    private ResultSet extractInfo(String sql) throws SQLException {
//        try (Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
//            try (PreparedStatement statement = con.prepareStatement(sql)) {
//                ResultSet result = statement.executeQuery();
//                return result;
//            }
////        throw new IllegalArgumentException("Wrong statement.");
//        }
//    }

//    private Connection getConnection() {
//        try {
//            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        throw new IllegalArgumentException("No connection.");
//    }
}
