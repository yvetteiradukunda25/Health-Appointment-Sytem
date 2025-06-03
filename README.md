Project Overview
Welcome! In this assignment, youʼll build a Healthcare Appointment System using Spring Boot and a PostgreSQL database. This
system lets users manage patients, doctors, appointments, medical records, and clinics. After logging in with a username and
password, any authenticated user can perform CRUD (Create, Read, Update, Delete) operations on all data. The project uses
JSON Web Tokens (JWT) for authentication, but there are no roles—everyone who logs in has the same access. Youʼll work with a
PostgreSQL database to store data across six tables with relationships like one-to-many and many-to-many. This project will help
you practice Spring Boot, JPA, REST APIs, and database design.
Learning Outcomes
Implement CRUD operations using Spring Boot and Spring Data JPA.
Design and manage complex relationships (one-to-many, many-to-one) between entities.
Integrate Spring Security with JWT for authentication (without roles).
Handle date-time data, form validation, and RESTful API design.
Practice advanced database queries and filtering (e.g., appointments by date or doctor).
Project Structure


Database Models
Youʼll create six tables in PostgreSQL using JPA entities. Below are the models with their fields, constraints, and relationships.
1. Patient
Represents a patient in the system.
Fields:
id : Long (Primary Key, auto-generated)
firstName : String (required, max 50 characters)
lastName : String (required, max 50 characters)
email : String (required, unique, valid email format)
phone : String (required, max 20 characters)
dateOfBirth : LocalDate (required, past date)
clinic : Clinic (many-to-one , a patient belongs to one clinic)
appointments : List (one-to-many , a patient has many appointments)
medicalRecords : List (one-to-many , a patient has many medical records)
Constraints: email  must be unique ; phone  must match a valid format (e.g., regex  for numbers); dateOfBirth  must be in
the past.
2. Doctor
Represents a doctor in the system.
Fields:
id : Long (Primary Key, auto-generated)
firstName : String (required, max 50 characters)
lastName : String (required, max 50 characters)
specialty : String (required, max 100 characters, e.g., "Cardiology") - Use Enums for clear and professional approach

email : String (required, unique, valid email format)
clinic : Clinic  (many-to-one , a doctor works at one clinic)
appointments : List (one-to-many , a doctor has many appointments)
Constraints: email must be unique.
3. Appointment
Represents an appointment between a patient and a doctor.
Fields:
id : Long (Primary Key, auto-generated)
patient : Patient (many-to-one, an appointment belongs to one patient)
doctor : Doctor (many-to-one, an appointment belongs to one doctor)
appointmentDate : LocalDateTime (required, future date)
status : String (required, e.g., "SCHEDULED", "COMPLETED", "CANCELLED") - Better to use Enums  here
notes : String (optional, max 500 characters)
Constraints: Unique combination of patient, doctor, and appointmentDate; appointmentDate must be in the future.
4. MedicalRecord
Represents a patientʼs medical record created by a doctor.
Fields:
id : Long (Primary Key, auto-generated)
patient : Patient (many-to-one, a record belongs to one patient)
doctor : Doctor (many-to-one, a record is created by one doctor)
diagnosis : String (required, max 255 characters)
prescription : String (optional, max 500 characters)
recordDate : LocalDateTime  (required, default to current date/time)
Constraints: recordDate must not be in the future.
5. Clinic
Represents a clinic where patients and doctors are associated.
Fields:
id : Long (Primary Key, auto-generated)
name : String (required, unique, max 100 characters)
address : String (required, max 255 characters)
phone : String (required, max 20 characters)
patients : List (one-to-many, a clinic has many patients)
doctors : List (one-to-many, a clinic has many doctors)
Constraints: name and phone must be unique.
6. User (for Authentication)
Manages user credentials for JWT authentication.
Fields:
id : Long (Primary Key, auto-generated)
firstName  : String (required, max 50 characters)
lastName  : String (required, max 50 characters)
email : String (required, unique, max 50 characters)
password : String (required, hashed using BCrypt )
Constraints: username must be unique; password must be hashed.
REST Endpoints
All endpoints (except authentication endpoints) require a JWT token in the Authorization header  (e.g., Bearer <token> ). Below
are the endpoints with examples.
Authentication Endpoints
POST /api/auth/login
Description: Log in to get a JWT token.
Request Body: { "email": "nprosper@gmail.com", "password": "pass123" }
Response: { "token": "eyJhbGciOiJIUzI1NiJ9..." }
Access: Public
POST /api/auth/register
Description: Register a new user.
Request Body: { "firstName": "john", "lastName": "john", "email": "prosper.rl@gmail.com", "password":
"pass123" }
Response: { "id": 1, "firstName": "john", "lastName": "john","email": "prosper.rk1@gmail.com" }
Access: Public
Patient Endpoints
POST /api/patients
Description: Create a new patient.
Request Body: { "firstName": "Alice", "lastName": "Smith", "email": "alice@example.com", "phone":
"1234567890", "dateOfBirth": "1990-05-15", "clinicId": 1 }
Response: { "id": 1, "firstName": "Alice", ... }
GET /api/patients
Description: Get all patients, filter by clinic, with pagination.
Query Parameters: clinicId=1 , page=0 , size=10
Response: [ { "id": 1, "firstName": "Alice", ... }, ... ]
GET /api/patients/{id}
Description: Get a patient by ID.
Example: /api/patients/1
Response: { "id": 1, "firstName": "Alice", ... }
PUT /api/patients/{id}
Description: Update a patient.
Request Body: { "firstName": "Alice", "lastName": "Jones", ... }
Response: Updated patient object
DELETE /api/patients/{id}
Description: Delete a patient (removes associated appointments and medical records).
Response: 204 No Content
Doctor Endpoints
POST /api/doctors
Description: Create a new doctor.
Request Body: { "firstName": "Bob", "lastName": "Lee", "specialty": "Cardiology", "email": "bob@example.com",
"clinicId": 1 }
Response: Doctor object
GET /api/doctors
Description: Get all doctors, filter by clinic or specialty.
Query Parameters: clinicId=1 , specialty=Cardiology
Response: List of Doctor objects
GET /api/doctors/{id}
Description: Get a doctor by ID.
Response: Doctor object
PUT /api/doctors/{id}
Description: Update a doctor.
Request Body: { "firstName": "Bob", "specialty": "Neurology", ... }
Response: Updated Doctor object
DELETE /api/doctors/{id}
Description: Delete a doctor (if no appointments or medical records).
Response: 204 No Content
Appointment Endpoints
POST /api/appointments
Description: Create an appointment.
Request Body: { "patientId": 1, "doctorId": 1, "appointmentDate": "2025-06-10T10:00:00", "status":
"SCHEDULED", "notes": "Check-up" }
Response: Appointment object
GET /api/appointments
Description: Get all appointments, filter by patient, doctor, or date.
Query Parameters: patientId=1 , doctorId=1 , date=2025-06-10
Response: List of Appointment objects
GET /api/appointments/{id}
Description: Get an appointment by ID.
Response: Appointment object
PUT /api/appointments/{id}
Description: Update an appointment.
Request Body: { "status": "COMPLETED", "notes": "Follow-up needed" }
Response: Updated Appointment object
DELETE /api/appointments/{id}
Description: Delete an appointment.
Response: 204 No Content
MedicalRecord Endpoints
POST /api/medical-records
Description: Create a medical record.
Request Body: { "patientId": 1, "doctorId": 1, "diagnosis": "Flu", "prescription": "Rest", "recordDate":
"2025-06-01T09:00:00" }
Response: MedicalRecord object
GET /api/medical-records
Description: Get all medical records, filter by patient or doctor.
Query Parameters: patientId=1 , doctorId=1
Response: List of MedicalRecord objects
GET /api/medical-records/{id}
Description: Get a medical record by ID.
Response: MedicalRecord object
PUT /api/medical-records/{id}
Description: Update a medical record.
Request Body: { "diagnosis": "Cold", "prescription": "Antibiotics" }
Response: Updated MedicalRecord object
DELETE /api/medical-records/{id}
Description: Delete a medical record.
Response: 204 No Content
Clinic Endpoints
POST /api/clinics
Description: Create a new clinic.
Request Body: { "name": "City Clinic", "address": "123 Main St", "phone": "555-1234" }
Response: Clinic object
GET /api/clinics
Description: Get all clinics.
Response: List of Clinic objects
GET /api/clinics/{id}
Description: Get a clinic by ID.
Response: Clinic object with patients and doctors
PUT /api/clinics/{id}
Description: Update a clinic.
Request Body: { "name": "New Clinic", "address": "456 Oak St", ... }
Response: Updated Clinic object
DELETE /api/clinics/{id}
Description: Delete a clinic (if no patients or doctors).
Response: 204 No Content
Assignment Guidelines
Deliverables:
Submit a GitHub repository with:
Complete source code.
README.md  explaining:
How to set up PostgreSQL and run the project.
API documentation (use Swagger).
Evaluation Criteria:
Correct CRUD Implementation: All endpoints work with proper HTTP status codes (e.g., 200, 201, 204, 400, 404).
Database Design: Correctly implemented relationships (one-to-many, many-to-many via Appointment) in PostgreSQL.
Authentication: All endpoints (except auth) require a valid JWT token.
Validation: Proper input validation (e.g., unique email, future appointment dates).
Code Quality: Clean, organized code with proper annotations and error handling
