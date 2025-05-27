-- USERS (EMPLOYEES и CLIENTS)
INSERT INTO users (id, first_name, last_name, email, password, is_enabled)
VALUES
    (1, 'John', 'Doe', 'john.doe@email.com', 'pass123', true),
    (2, 'Jane', 'Smith', 'jane.smith@email.com', 'abc456', true),
    (3, 'Bob', 'Jones', 'bob.jones@email.com', 'qwerty789', true),
    (4, 'Alice', 'White', 'alice.white@email.com', 'secret567', true),
    (5, 'Mike', 'Wilson', 'mike.wilson@email.com', 'mypassword', true),
    (6, 'Sara', 'Brown', 'sara.brown@email.com', 'letmein123', true),
    (7, 'Tom', 'Jenkins', 'tom.jenkins@email.com', 'pass4321', true),
    (8, 'Lisa', 'Taylor', 'lisa.taylor@email.com', 'securepwd', true),
    (9, 'David', 'Wright', 'david.wright@email.com', 'access123', true),
    (10, 'Emily', 'Harris', 'emily.harris@email.com', '1234abcd', true),
    (11, 'Medelyn', 'Wright', 'client1@example.com', 'password123', true),
    (12, 'Landon', 'Phillips', 'client2@example.com', 'securepass', true),
    (13, 'Harmony', 'Mason', 'client3@example.com', 'abc123', true),
    (14, 'Archer', 'Harper', 'client4@example.com', 'pass456', true),
    (15, 'Kira', 'Jacobs', 'client5@example.com', 'letmein789', true),
    (16, 'Maximus', 'Kelly', 'client6@example.com', 'adminpass', true),
    (17, 'Sierra', 'Mitchell', 'client7@example.com', 'mypassword', true),
    (18, 'Quinton', 'Saunders', 'client8@example.com', 'test123', true),
    (19, 'Amina', 'Clarke', 'client9@example.com', 'qwerty123', true),
    (20, 'Bryson', 'Chavez', 'client10@example.com', 'pass789', true);

-- EMPLOYEES (наследование от users по user_id)
INSERT INTO employees (user_id, birth_date, phone)
VALUES
    (1, '1990-05-15', '555-123-4567'),
    (2, '1985-09-20', '555-987-6543'),
    (3, '1978-03-08', '555-321-6789'),
    (4, '1982-11-25', '555-876-5432'),
    (5, '1995-07-12', '555-234-5678'),
    (6, '1989-01-30', '555-876-5433'),
    (7, '1975-06-18', '555-345-6789'),
    (8, '1987-12-04', '555-789-0123'),
    (9, '1992-08-22', '555-456-7890'),
    (10, '1980-04-10', '555-098-7654');

-- CLIENTS (наследование от users по user_id)
INSERT INTO clients (user_id, balance)
VALUES
    (11, 1000.00),
    (12, 1500.50),
    (13, 800.75),
    (14, 1200.25),
    (15, 900.80),
    (16, 1100.60),
    (17, 1300.45),
    (18, 950.30),
    (19, 1050.90),
    (20, 880.20);

-- BOOKS
INSERT INTO books (name, genre, target_age_group, price, publication_date, author, pages, characteristics, description, language)
VALUES
    ('The Hidden Treasure', 'Adventure', 'ADULT', 24.99, '2018-05-15', 'Emily White', 400, 'Mysterious journey', 'An enthralling adventure of discovery', 'ENGLISH'),
    ('Echoes of Eternity', 'Fantasy', 'TEEN', 16.50, '2011-01-15', 'Daniel Black', 350, 'Magical realms', 'A spellbinding tale of magic and destiny', 'ENGLISH'),
    ('Whispers in the Shadows', 'Mystery', 'ADULT', 29.95, '2018-08-11', 'Sophia Green', 450, 'Intriguing suspense', 'A gripping mystery that keeps you guessing', 'ENGLISH'),
    ('The Starlight Sonata', 'Romance', 'ADULT', 21.75, '2011-05-15', 'Michael Rose', 320, 'Heartwarming love story', 'A beautiful journey of love and passion', 'ENGLISH'),
    ('Beyond the Horizon', 'Science Fiction', 'CHILD', 18.99, '2004-05-15', 'Alex Carter', 280, 'Interstellar adventure', 'An epic sci-fi adventure beyond the stars', 'ENGLISH'),
    ('Dancing with Shadows', 'Thriller', 'ADULT', 26.50, '2015-05-15', 'Olivia Smith', 380, 'Suspenseful twists', 'A thrilling tale of danger and intrigue', 'ENGLISH'),
    ('Voices in the Wind', 'Historical Fiction', 'ADULT', 32.00, '2017-05-15', 'William Turner', 500, 'Rich historical setting', 'A compelling journey through time', 'ENGLISH'),
    ('Serenade of Souls', 'Fantasy', 'TEEN', 15.99, '2013-05-15', 'Isabella Reed', 330, 'Enchanting realms', 'A magical fantasy filled with wonder', 'ENGLISH'),
    ('Silent Whispers', 'Mystery', 'ADULT', 27.50, '2021-05-15', 'Benjamin Hall', 420, 'Intricate detective work', 'A mystery that keeps you on the edge', 'ENGLISH'),
    ('Whirlwind Romance', 'Romance', 'OTHER', 23.25, '2022-05-15', 'Emma Turner', 360, 'Passionate love affair', 'A romance that sweeps you off your feet', 'ENGLISH');
