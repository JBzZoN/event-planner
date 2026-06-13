CREATE TYPE planner_status AS ENUM (
    'PENDING_VERIFICATION',
    'ACTIVE',
    'SUSPENDED',
    'INACTIVE'
);

CREATE TYPE user_role AS ENUM (
    'USER',
    'ADMIN',
    'VENDOR'
);

CREATE TYPE service_type AS ENUM (
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
);

CREATE TYPE report_status AS ENUM (
    'UNDER_REVIEW',
    'RESOLVED',
    'REJECTED'
);

CREATE TYPE verification_status AS ENUM (
    'PENDING',
    'APPROVED',
    'REGISTERED'
);



CREATE TABLE planner_detail (
    org_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    org_name VARCHAR(100),
    office_address VARCHAR(255),
    status planner_status,
    suspended_date DATE
);



CREATE TABLE user_detail (
    user_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    org_id INT,
    name VARCHAR(100),
    phone VARCHAR(20),
    address VARCHAR(255),
    username VARCHAR(50),
    password VARCHAR(100),
    role user_role,
    email_address VARCHAR(100),

    CONSTRAINT fk_user_org
        FOREIGN KEY (org_id)
        REFERENCES planner_detail(org_id)
);



CREATE TABLE type_table (
    type_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    type_code service_type
);



CREATE TABLE package (
    package_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    org_id INT,
    package_name VARCHAR(100),
    package_price DECIMAL(10,2),

    CONSTRAINT fk_package_org
        FOREIGN KEY (org_id)
        REFERENCES planner_detail(org_id)
);



CREATE TABLE planner_type (
    org_id INT,
    type_id INT,

    PRIMARY KEY (org_id, type_id),

    CONSTRAINT fk_planner_type_org
        FOREIGN KEY (org_id)
        REFERENCES planner_detail(org_id),

    CONSTRAINT fk_planner_type_type
        FOREIGN KEY (type_id)
        REFERENCES type_table(type_id)
);



CREATE TABLE package_type (
    package_id INT,
    type_id INT,

    PRIMARY KEY (package_id, type_id),

    CONSTRAINT fk_package_type_package
        FOREIGN KEY (package_id)
        REFERENCES package(package_id),

    CONSTRAINT fk_package_type_type
        FOREIGN KEY (type_id)
        REFERENCES type_table(type_id)
);



CREATE TABLE package_group (
    group_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    group_name VARCHAR(100),
    package_id INT,

    CONSTRAINT fk_group_package
        FOREIGN KEY (package_id)
        REFERENCES package(package_id)
);



CREATE TABLE package_group_item (
    item_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    group_id INT,
    item_name VARCHAR(100),
    item_price DECIMAL(10,2),
    package_id INT,

    CONSTRAINT fk_item_group
        FOREIGN KEY (group_id)
        REFERENCES package_group(group_id),

    CONSTRAINT fk_item_package
        FOREIGN KEY (package_id)
        REFERENCES package(package_id)
);



CREATE TABLE reports (
    report_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    org_id INT NOT NULL,
    reason TEXT NOT NULL,
    status report_status,
    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_report_org
        FOREIGN KEY (org_id)
        REFERENCES planner_detail(org_id)
);



CREATE TABLE vendor_verification (
    verification_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    org_id INT NOT NULL UNIQUE,
    status verification_status,
    remarks TEXT,
    gst_registration_certificate VARCHAR(100),
    verified_date DATE,

    CONSTRAINT fk_verification_org
        FOREIGN KEY (org_id)
        REFERENCES planner_detail(org_id)
);