-- Create persons table
CREATE TABLE IF NOT EXISTS persons (
    person_id INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    phone VARCHAR(30) NOT NULL,
    dni_type ENUM('CC', 'CE', 'TI', 'PP', 'OTRO') NOT NULL DEFAULT 'CC',
    dni_number VARCHAR(20) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (person_id)
);

-- Create auths table
CREATE TABLE IF NOT EXISTS auths (
    auth_id INT NOT NULL AUTO_INCREMENT,
    person_id INT NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    `password` VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (auth_id),
    FOREIGN KEY (person_id) REFERENCES persons(person_id)
);

-- Create roles table
CREATE TABLE IF NOT EXISTS roles (
    role_id INT NOT NULL AUTO_INCREMENT,
    person_id INT NOT NULL UNIQUE,
    `name` ENUM('ADMIN', 'PATIENT', 'STAFF') NOT NULL DEFAULT 'PATIENT',
    office VARCHAR(80),
    position VARCHAR(50),
    `status` ENUM('ENROLLED', 'NOT_LINKED', 'BANNED') NOT NULL DEFAULT 'ENROLLED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (role_id),
    FOREIGN KEY (person_id) REFERENCES persons(person_id)
);

-- Create patients table
CREATE TABLE IF NOT EXISTS patients (
    patient_id INT NOT NULL AUTO_INCREMENT,
    person_id INT NOT NULL UNIQUE,
    `address` VARCHAR(100),
    `location` VARCHAR(100) NOT NULL,
    last_visit TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (patient_id),
    FOREIGN KEY (person_id) REFERENCES persons(person_id)
);

-- Create facilities table
CREATE TABLE IF NOT EXISTS facilities (
    facility_id INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    room_type ENUM('CONSULTATION', 'SURGERY', 'RECOVERY', 'LABORATORY', 'ADMINISTRATIVE') NOT NULL DEFAULT 'ADMINISTRATIVE',
    room_location VARCHAR(30) NOT NULL,
    room_number INT NOT NULL,
    department VARCHAR(50),
    equipment TEXT,
    capacity INT NOT NULL,
    available BOOLEAN NOT NULL DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (facility_id)
);

-- Create staffs table
CREATE TABLE IF NOT EXISTS staffs (
    staff_id INT NOT NULL AUTO_INCREMENT,
    person_id INT NOT NULL UNIQUE,
    facility_id INT UNIQUE,
    `type` ENUM('DOCTOR', 'SPECIALIZE', 'ADMINISTRATIVE') NOT NULL DEFAULT 'DOCTOR',
    in_person BOOLEAN NOT NULL DEFAULT 1,
    specialty VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (staff_id),
    FOREIGN KEY (person_id) REFERENCES persons(person_id)
);

-- Create consultations table
CREATE TABLE IF NOT EXISTS consultations (
    consultation_id INT NOT NULL AUTO_INCREMENT,
    patient_id INT NOT NULL,
    staff_id INT NOT NULL,
    `location` VARCHAR(100) NOT NULL,
    `date` TIMESTAMP NOT NULL,
    `type` ENUM('CONSULTATION', 'SURGERY', 'EXAMINATION', 'LABORATORY', 'APPOINTMENT') NOT NULL DEFAULT 'CONSULTATION',
    `status` ENUM('PENDING', 'CONFIRMED', 'CANCELLED', 'COMPLETED') NOT NULL DEFAULT 'PENDING',
    virtual_queue INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (consultation_id),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (staff_id) REFERENCES staffs(staff_id)
);

-- Create examinations table
CREATE TABLE IF NOT EXISTS examinations (
    examination_id INT NOT NULL AUTO_INCREMENT,
    consultation_id INT NOT NULL,
    `date` TIMESTAMP NOT NULL,
    `type` ENUM('BLOOD', 'URINE', 'XRAY', 'CTSCAN', 'MRI', 'ECG', 'ECHO', 'ENDOSCOPY', 'COLONOSCOPY', 'BIOPSY', 'OTHER') NOT NULL,
    result TEXT,
    laboratory VARCHAR(50),
    observation TEXT,
    `status` ENUM('PENDING', 'CONFIRMED', 'CANCELLED', 'COMPLETED') NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (examination_id),
    FOREIGN KEY (consultation_id) REFERENCES consultations(consultation_id)
);

-- Create inventories table
CREATE TABLE IF NOT EXISTS inventories (
    inventory_id INT NOT NULL AUTO_INCREMENT,
    item_name VARCHAR(50) NOT NULL,
    item_type ENUM('MEDICINE', 'EQUIPMENT', 'SUPPLIES', 'OTHER') NOT NULL,
    item_description TEXT,
    item_stock INT NOT NULL DEFAULT 0,
    item_status ENUM('AVAILABLE', 'OUT_OF_STOCK', 'EXPIRED', 'DAMAGED') NOT NULL DEFAULT 'AVAILABLE',
    concentration VARCHAR(50),
    made_by VARCHAR(50) NOT NULL,
    route_of_administration VARCHAR(50),
    dosage VARCHAR(50),
    frequency VARCHAR(50),
    duration VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (inventory_id)
);

-- Create medical records table
CREATE TABLE IF NOT EXISTS medical_records (
    medical_record_id INT NOT NULL AUTO_INCREMENT,
    patient_id INT NOT NULL,
    age INT NOT NULL,
    weight DECIMAL(5, 2) NOT NULL,
    family_history TEXT,
    clinical_evolution TEXT,
    observation TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (medical_record_id),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);

-- Create records_examinations table
CREATE TABLE IF NOT EXISTS records_examinations (
    medical_record_id INT NOT NULL,
    examination_id INT NOT NULL,
    PRIMARY KEY (medical_record_id, examination_id),
    FOREIGN KEY (medical_record_id) 
        REFERENCES medical_records(medical_record_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (examination_id) 
        REFERENCES examinations(examination_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Create prescriptions table
CREATE TABLE IF NOT EXISTS prescriptions (
    prescription_id INT NOT NULL AUTO_INCREMENT,
    consultation_id INT NOT NULL UNIQUE,
    staff_id INT NOT NULL,
    `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `condition` TEXT,
    concurrencies TEXT,
    justification TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (prescription_id),
    FOREIGN KEY (staff_id) REFERENCES staffs(staff_id),
    FOREIGN KEY (consultation_id) REFERENCES consultations(consultation_id)
);

-- Create medicines table
CREATE TABLE IF NOT EXISTS medicines (
    medicine_id INT NOT NULL AUTO_INCREMENT,
    prescription_id INT NOT NULL,
    item_name VARCHAR(50) NOT NULL,
    inventory_id INT NOT NULL,
    item_quantity INT NOT NULL,
    item_status ENUM('PENDING', 'DELIVERED') NOT NULL DEFAULT 'PENDING',
    concentration VARCHAR(50) NOT NULL,
    made_by VARCHAR(50) NOT NULL,
    route_of_administration VARCHAR(50),
    dosage VARCHAR(50),
    frequency VARCHAR(50),
    duration VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (medicine_id),
    FOREIGN KEY (prescription_id) REFERENCES prescriptions(prescription_id)
);