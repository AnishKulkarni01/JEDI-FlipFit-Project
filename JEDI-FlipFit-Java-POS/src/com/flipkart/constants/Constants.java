package com.flipkart.constants;

public class Constants {


        // ------------------------ GYM OWNER ------------------------
        public static final String FETCH_ALL_GYM_OWNERS_QUERY = "SELECT * FROM jedi_flipfit_schema.GymOwner";
        public static final String FETCH_ALL_PENDING_GYM_OWNERS_QUERY = "SELECT * FROM jedi_flipfit_schema.GymOwner where isApproved = 2";
        public static final String SEND_GYM_OWNER_APPROVAL_REQ_QUERY = "UPDATE jedi_flipfit_schema.GymOwner SET isApproved = 2 WHERE Id =?;";

        public static final String ADD_GYM_CENTRE_QUERY = "INSERT INTO jedi_flipfit_schema.GymCentre (centreId, ownerId, centreName, gstin, city, capacity, price, isApproved) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
//    public static final String ADD_GYM_CENTRE_QUERY = "INSERT INTO jedi_flipfit_schema.GymCentre (centreId, ownerId, centreName, gstin, city, capacity, price, isApproved) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";



        //  ------------ GYM OWNER ------------
        public static final String REGISTER_GYM_OWNER = "Insert into GymOwner values (?,?,?,?,?,?,?)";
        public static final String LOGIN_GYM_OWNER = "Select * from GymOwner where name=? and password=?";
        public static final String SQL_APPROVE_GYM_OWNER_BY_ID_QUERY="Update GymOwner Set isApproved=? WHERE Id=?";
        public static final String UPDATE_GYM_OWNER_DETAILS = "UPDATE jedi_flipfit_schema.GymOwner SET ? = ? WHERE gymOwnerId =?;";
        public static final String GET_GYM_OWNER_BY_ID = "SELECT * FROM jedi_flipfit_schema.GymOwner WHERE gymOwnerId = ?;";
        public static final String ADD_NEW_GYM_OWNER = "INSERT INTO jedi_flipfit_schema.GymOwner (username,password,email,contact) VALUES ( ?, ?, ?, ?);";
        public static final String GET_GYM_OWNER_BY_USERNAME = "SELECT * FROM jedi_flipfit_schema.Customer WHERE username = ?;";




//    public static final String SQL_APPROVE_GYM_OWNER_BY_ID_QUERY ="Update GymOwner Set isApproved=? WHERE Id=?";


        // ------------------------ GYM CENTRE ------------------------
        public static final String FETCH_GYM_CENTRES_BY_OWNER_ID = "SELECT * FROM jedi_flipfit_schema.GymCentre where ownerId = ?";
        public static final String FETCH_ALL_PENDING_GYM_REQUESTS = "SELECT * FROM jedi_flipfit_schema.Gym where isApproved = false";
        public static final String FETCH_ALL_PENDING_GYM_REQUESTS_BY_GYMOWNERID = "SELECT * FROM jedi_flipfit_schema.Gym where gymOwnerId = ?";

        public static final String APPROVE_GYM_BY_ID="Update Gym Set isApproved=? WHERE gymId=?";
        public static final String FETCH_GYM_CENTRES_BY_CITY = "SELECT * FROM jedi_flipfit_schema.GymCentre where city = ?";
        public static final String SEND_GYM_ONBOARD_REQUEST = "INSERT INTO jedi_flipfit_schema.Gym (name,city,gstin,seats,gymOwnerId) VALUES ( ?, ?, ?, ?,?);";
        public static final String UPDATE_GYM_DETAILS = "UPDATE jedi_flipfit_schema.Gym SET ? = ? WHERE gymId =?;";


//    public static final String SQL_APPROVE_GYM_CENTRE_BY_ID_QUERY = "Update GymCentre Set isApproved=? WHERE centreId=?";



        // ------------------------ CUSTOMER ------------------------
//    public static final String ADD_NEW_CUSTOMER = "INSERT INTO jedi_flipfit_schema.GymCentre (userId,userName,password,email,phoneNumber,cardNumber) VALUES (?, ?, ?, ?, ?, ?);";






        //    ------- Customer ---------------
        public static final String CUSTOMER_LOGIN_QUERY = "SELECT * FROM jedi_flipfit_schema.Customer WHERE username = ? AND password = ?";
        public static final String ADD_NEW_CUSTOMER = "INSERT INTO jedi_flipfit_schema.Customer (username,password,email,contact) VALUES ( ?, ?, ?, ?);";
        public static final String GET_CUSTOMER_BY_ID = "SELECT * FROM jedi_flipfit_schema.Customer WHERE customerId = ?;";
        public static final String GET_CUSTOMER_BY_USERNAME = "SELECT * FROM jedi_flipfit_schema.Customer WHERE username = ?;";
        public static final String UPDATE_CUSTOMER_DETAILS = "UPDATE jedi_flipfit_schema.Customer SET ? = ? WHERE customerId =?;";



        //  ----------- Booking -----------
        public static final String GET_BOOKING_BY_CUSTOMER_ID ="Select * From jedi_flipfit_schema.Booking where customerId = ?";
        public static final String CANCEL_BOOKING_BY_ID= "Delete from jedi_flipfit_schema.Booking where bookingId = ?";
        public static final String ADD_BOOKING= "INSERT INTO jedi_flipfit_schema.Booking (customerID, slotId) values(?, ?)";

        // ------------- Schedule -------------
        public static final String ADD_SCHEDULE="INSERT INTO jedi_flipfit_schema.Schedule(scheduleId, date, slotId, availability ) values (?,?,?,?)";
        public static final String GET_SCHEDULES_BY_DATE="SELECT * FROM jedi_flipfit_schema.Schedule WHERE date=?";
        public static final String GET_SCHEDULE_BY_ID ="SELECT * FROM jedi_flipfit_schema.Schedule WHERE scheduleId=?";
        public static final String MODIFY_SCHEDULE_AVAILABILITY ="UPDATE `flipfit`.`schedule` SET availability = ? WHERE (`scheduleId` = ?)";

        // ---------------- Slot ----------------
        public static final String FETCH_ALL_SLOTS ="SELECT * FROM jedi_flipfit_schema.Slot";
        public static final String FETCH_SLOT_BY_GYMID ="SELECT * FROM jedi_flipfit_schema.Slot WHERE gymId=?";
        public static final String ADD_SLOT ="INSERT INTO jedi_flipfit_schema.Slot( gymId,date, startTime) values (?, ?, ?)";
        public static final String FETCH_SLOT_BY_ID = "SELECT * FROM jedi_flipfit_schema.Slot WHERE slotId=?";
        public static final String UPDATE_SLOT_DETAILS = "UPDATE jedi_flipfit_schema.Slot SET ? = ? WHERE slotId =?;";

        public static final String FETCH_SLOT_BY_ID_AND_CENTRE_ID = "SELECT * FROM jedi_flipfit_schema.Slot WHERE slotId=? AND centreId=?";

        public static final String GET_BOOKING_BY_BOOKING_ID ="Select * From jedi_flipfit_schema.Booking where bookingId = ?";


        public static final String GET_USERPLAN_BY_CUSTOMER_ID = "select * from slot join schedule where slot.slotId=schedule.slotId and schedule.scheduleId=?";

}
