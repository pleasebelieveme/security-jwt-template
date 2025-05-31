-- 초기 데이터
INSERT INTO users (email, password, name, nickname, user_role, created_at, updated_at, deleted_at)
VALUES ('test@test.com', 'Test1234!@', 'testman', 'testnickname', 'USER', NOW(), NOW(), null);