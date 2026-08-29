-- ============================================================
-- 1. PLANNER DETAILS
-- ============================================================

INSERT INTO planner_detail (
    org_name,
    office_address,
    status,
    suspended_date
)
VALUES
(
    'EverAfter Events',
    'MG Road, Kochi, Kerala',
    'ACTIVE',
    NULL
),
(
    'Royal Moments Weddings',
    'Vyttila, Kochi, Kerala',
    'PENDING_VERIFICATION',
    NULL
);


-- ============================================================
-- 2. USER DETAILS
-- Password is NULL because authentication is handled by Keycloak.
-- ============================================================

INSERT INTO user_detail (
    org_id,
    name,
    phone,
    address,
    role,
    email_address
)
VALUES
(
    (SELECT org_id
     FROM planner_detail
     WHERE org_name = 'EverAfter Events'),
    'Josh Thomas',
    '9876543210',
    'Kochi, Kerala',
    'VENDOR',
    'josh@example.com'
),
(
    (SELECT org_id
     FROM planner_detail
     WHERE org_name = 'EverAfter Events'),
    'Rahul Menon',
    '9876543211',
    'Aluva, Kerala',
    'USER',
    'rahul@example.com'
);


-- ============================================================
-- 3. SERVICE TYPES
-- ============================================================

INSERT INTO type_table (type_code)
VALUES
('PHOTOGRAPHY'),
('CATERING'),
('DECORATION'),
('DJ');


-- ============================================================
-- 4. PLANNER TYPES
-- EverAfter Events provides Photography, Catering, Decoration
-- ============================================================

INSERT INTO planner_type (
    org_id,
    type_id
)
VALUES
(
    (SELECT org_id
     FROM planner_detail
     WHERE org_name = 'EverAfter Events'),
    (SELECT type_id
     FROM type_table
     WHERE type_code = 'PHOTOGRAPHY')
),
(
    (SELECT org_id
     FROM planner_detail
     WHERE org_name = 'EverAfter Events'),
    (SELECT type_id
     FROM type_table
     WHERE type_code = 'CATERING')
),
(
    (SELECT org_id
     FROM planner_detail
     WHERE org_name = 'EverAfter Events'),
    (SELECT type_id
     FROM type_table
     WHERE type_code = 'DECORATION')
);


-- ============================================================
-- 5. PACKAGES
-- ============================================================

INSERT INTO package (
    org_id,
    package_name,
    package_price
)
VALUES
(
    (SELECT org_id
     FROM planner_detail
     WHERE org_name = 'EverAfter Events'),
    'Premium Wedding Package',
    75000.00
),
(
    (SELECT org_id
     FROM planner_detail
     WHERE org_name = 'EverAfter Events'),
    'Photography Package',
    35000.00
);


-- ============================================================
-- 6. PACKAGE TYPES
-- Premium Wedding Package -> Photography, Catering, Decoration
-- ============================================================

INSERT INTO package_type (
    package_id,
    type_id
)
VALUES
(
    (SELECT package_id
     FROM package
     WHERE package_name = 'Premium Wedding Package'),
    (SELECT type_id
     FROM type_table
     WHERE type_code = 'PHOTOGRAPHY')
),
(
    (SELECT package_id
     FROM package
     WHERE package_name = 'Premium Wedding Package'),
    (SELECT type_id
     FROM type_table
     WHERE type_code = 'CATERING')
),
(
    (SELECT package_id
     FROM package
     WHERE package_name = 'Premium Wedding Package'),
    (SELECT type_id
     FROM type_table
     WHERE type_code = 'DECORATION')
);


-- ============================================================
-- 7. PACKAGE GROUPS
-- ============================================================

INSERT INTO package_group (
    group_name,
    package_id
)
VALUES
(
    'Photography Services',
    (SELECT package_id
     FROM package
     WHERE package_name = 'Premium Wedding Package')
),
(
    'Food Services',
    (SELECT package_id
     FROM package
     WHERE package_name = 'Premium Wedding Package')
);


-- ============================================================
-- 8. PACKAGE GROUP ITEMS
-- ============================================================

INSERT INTO package_group_item (
    group_id,
    item_name,
    item_price,
    package_id
)
VALUES
(
    (
        SELECT group_id
        FROM package_group
        WHERE group_name = 'Photography Services'
    ),
    'Full Day Photography',
    25000.00,
    (
        SELECT package_id
        FROM package
        WHERE package_name = 'Premium Wedding Package'
    )
),
(
    (
        SELECT group_id
        FROM package_group
        WHERE group_name = 'Photography Services'
    ),
    'Cinematic Wedding Album',
    10000.00,
    (
        SELECT package_id
        FROM package
        WHERE package_name = 'Premium Wedding Package'
    )
),
(
    (
        SELECT group_id
        FROM package_group
        WHERE group_name = 'Food Services'
    ),
    'Traditional Kerala Buffet',
    30000.00,
    (
        SELECT package_id
        FROM package
        WHERE package_name = 'Premium Wedding Package'
    )
);


-- ============================================================
-- 9. REPORTS
-- ============================================================

INSERT INTO reports (
    org_id,
    reason,
    status,
    created_at
)
VALUES
(
    (
        SELECT org_id
        FROM planner_detail
        WHERE org_name = 'EverAfter Events'
    ),
    'Customer reported a delay in service delivery.',
    'UNDER_REVIEW',
    CURRENT_TIMESTAMP
);


-- ============================================================
-- 10. VENDOR VERIFICATION
-- ============================================================

INSERT INTO vendor_verification (
    org_id,
    status,
    registration_date,
    verified_date,
    remarks,
    gst_registration_certificate
)
VALUES
(
    (
        SELECT org_id
        FROM planner_detail
        WHERE org_name = 'EverAfter Events'
    ),
    'APPROVED',
    CURRENT_TIMESTAMP - INTERVAL '10 days',
    CURRENT_TIMESTAMP - INTERVAL '5 days',
    'GST certificate verified successfully.',
    'gst/everafter_gst_certificate.pdf'
);


-- ============================================================
-- 11. BOOKING
-- Rahul books the Premium Wedding Package
-- ============================================================

INSERT INTO booking (
    user_id,
    package_id,
    booking_amount,
    booking_date
)
VALUES
(
    (
        SELECT user_id
        FROM user_detail
        WHERE email_address = 'rahul@example.com'
    ),
    (
        SELECT package_id
        FROM package
        WHERE package_name = 'Premium Wedding Package'
    ),
    75000.00,
    CURRENT_TIMESTAMP
);