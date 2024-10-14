-- 와이파이 정보 테이블
CREATE TABLE IF NOT EXISTS Wifi_Info (
    wifi_mgr_no VARCHAR(20) PRIMARY KEY,
    wifi_name VARCHAR(255),
    address1 VARCHAR(255),
    address2 VARCHAR(255),
    floor VARCHAR(50),
    install_type VARCHAR(100),
    service_type VARCHAR(100),
    communication_network VARCHAR(100),
    construction_year INT,
    indoor_outdoor VARCHAR(50),
    latitude DECIMAL(10,8),
    longitude DECIMAL(11,8),
    work_dttm DATETIME,
    location_id INT,
    agency_id INT
    );

-- 히스토리 테이블
CREATE TABLE IF NOT EXISTS History (
    history_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_lat DECIMAL(10,8),
    user_lnt DECIMAL(11,8),
    search_time DATETIME DEFAULT CURRENT_TIMESTAMP
    );

-- 히스토리-와이파이 연결 테이블
CREATE TABLE IF NOT EXISTS History_Wifi_Info (
    history_id INT,
    wifi_mgr_no VARCHAR(20),
    PRIMARY KEY (history_id, wifi_mgr_no),
    FOREIGN KEY (history_id) REFERENCES History(history_id),
    FOREIGN KEY (wifi_mgr_no) REFERENCES Wifi_Info(wifi_mgr_no)
    );
