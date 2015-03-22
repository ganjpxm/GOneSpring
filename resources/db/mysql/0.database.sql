DROP DATABASE IF EXISTS gone;
CREATE DATABASE gone DEFAULT CHARACTER SET utf8;

use jpdb;

##new user
GRANT ALL ON gone.* TO ganjp@'175.156.107.251' IDENTIFIED BY 'ganjp';

##修改用户信息
#update user set password=password('ganjp') where user='root';


##删除用户
#DELETE FROM user WHERE User="ganjp";

COMMIT;
flush privileges;
