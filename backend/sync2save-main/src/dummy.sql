INSERT INTO users (name) VALUES
('Maarten de Vries'),
('Sven Karlsson'),
('Emir Yıldırım'),
('Omar Al-Farsi'),
('Sanne Jansen'),
('Astrid Lindström'),
('Leyla Demir'),
('Fatima El-Sayed');


INSERT INTO issues (description, category, user_id) VALUES
('Login page is not responsive', 'UI Bug', 1),  -- Maarten de Vries
('Database connection times out', 'Backend Issue', 2),  -- Sven Karlsson
('Cannot reset password', 'Authentication', 3),  -- Emir Yıldırım
('Page loads too slow', 'Performance', 4),  -- Omar Al-Farsi
('Dropdown menu does not work on mobile', 'UI Bug', 5),  -- Sanne Jansen
('API returns incorrect data', 'Backend Issue', 6),  -- Astrid Lindström
('User profile pictures do not load', 'Frontend Bug', 7),  -- Leyla Demir
('Translation issues in Arabic locale', 'Localization', 8),  -- Fatima El-Sayed
('Session expires too quickly', 'Security', 1),  -- Maarten de Vries
('Unable to delete user account', 'Account Management', 3),  -- Emir Yıldırım
('Search results are not relevant', 'UX', 5),  -- Sanne Jansen
('Email notifications are not sent', 'Notification System', 7);  -- Leyla Demir
