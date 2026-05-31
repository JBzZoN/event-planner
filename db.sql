DROP DATABASE IF EXISTS event_planner;
CREATE DATABASE event_planner;
USE event_planner;

DROP USER IF EXISTS 'event_user'@'localhost';

CREATE USER 'event_user'@'localhost' IDENTIFIED BY 'event_planner';

GRANT ALL PRIVILEGES ON event_planner.* TO 'event_user'@'localhost';

FLUSH PRIVILEGES;

CREATE TABLE planner_detail (
    org_id INT PRIMARY KEY AUTO_INCREMENT,
    org_name VARCHAR(100),
    office_address VARCHAR(255),
    status ENUM(
        'PENDING_VERIFICATION',
        'ACTIVE',
        'SUSPENDED',
        'INACTIVE'
    ),
    suspended_date DATE
);

CREATE TABLE user_detail (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    org_id INT,
    name VARCHAR(100),
    phone VARCHAR(20),
    address VARCHAR(255),
    username VARCHAR(50),
    password VARCHAR(100),
    role ENUM('USER', 'ADMIN', 'VENDOR'),
    email_address VARCHAR(100),

    FOREIGN KEY (org_id) REFERENCES planner_detail(org_id)
);

CREATE TABLE type_table (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_code ENUM(
        'PHOTOGRAPHY',
        'VIDEOGRAPHY',
        'FOOD',
        'CATERING',
        'DECORATION',
        'FLOWERS',
        'MAKEUP',
        'MEHENDI',
        'MUSIC',
        'DJ',
        'LIVE_BAND',
        'LIGHTING',
        'STAGE_SETUP',
        'SOUND_SYSTEM',
        'TRANSPORTATION',
        'ACCOMMODATION',
        'SECURITY',
        'INVITATION',
        'CAKE',
        'ENTERTAINMENT'
    )
);

CREATE TABLE package (
    package_id INT PRIMARY KEY AUTO_INCREMENT,
    org_id INT,
    package_name VARCHAR(100),
    package_price DECIMAL(10,2),

    FOREIGN KEY (org_id)
        REFERENCES planner_detail(org_id)
);

CREATE TABLE planner_type (
    org_id INT,
    type_id INT,

    PRIMARY KEY (org_id, type_id),

    FOREIGN KEY (org_id)
        REFERENCES planner_detail(org_id),

    FOREIGN KEY (type_id)
        REFERENCES type_table(type_id)
);

CREATE TABLE package_type (
    package_id INT,
    type_id INT,

    PRIMARY KEY (package_id, type_id),

    FOREIGN KEY (package_id)
        REFERENCES package(package_id),

    FOREIGN KEY (type_id)
        REFERENCES type_table(type_id)
);

CREATE TABLE package_group (
    group_id INT PRIMARY KEY AUTO_INCREMENT,
    group_name VARCHAR(100),
    package_id INT,

    FOREIGN KEY (package_id)
        REFERENCES package(package_id)
);

CREATE TABLE package_group_item (
    item_id INT PRIMARY KEY AUTO_INCREMENT,
    group_id INT,
    item_name VARCHAR(100),
    item_price DECIMAL(10,2),
    package_id INT,

    FOREIGN KEY (group_id)
        REFERENCES package_group(group_id),

    FOREIGN KEY (package_id)
        REFERENCES package(package_id)
);

CREATE TABLE reports (
    report_id INT PRIMARY KEY AUTO_INCREMENT,
    org_id INT NOT NULL,
    reason TEXT NOT NULL,
    status ENUM(
        'UNDER_REVIEW',
        'RESOLVED',
        'REJECTED'
    ),
    created_at DATETIME NOT NULL,

    FOREIGN KEY (org_id)
        REFERENCES planner_detail(org_id)
);

CREATE TABLE vendor_verification (
    verification_id INT PRIMARY KEY AUTO_INCREMENT,
    org_id INT NOT NULL UNIQUE,
    status ENUM(
        'PENDING',
        'APPROVED',
        'REGISTERED'
    ),
    remarks TEXT,
    gst_registration_certificate VARCHAR(100),
    verified_date DATE,

    FOREIGN KEY (org_id)
        REFERENCES planner_detail(org_id)
);
