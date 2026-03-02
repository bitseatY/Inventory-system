create table Categories( id bigint primary key auto_increment,
                        name varchar(200)unique,
                        description varchar(300) not null);


create table products(id bigint primary key auto_increment,
                cat_id bigint,
               name varchar(200) unique,
               description varchar(300) not null ,
               selling_price decimal(10,2),
               quantity_in_stock  int ,
               Unit_of_measurement varchar(20) not null,
               foreign key (cat_id) references categories(id),
               constraint chk_q check(quantity_in_stock>=0),
               constraint chk_p check(selling_price>0)

               );

 create table users (id bigint primary key auto_increment,
                     name varchar(50) unique,
                     role enum ('STAFF','MANAGER') not null,
                     password varchar(100) not null);




create table stock_change_records(id bigint primary key auto_increment,
             pro_id bigint,
             reason enum('RESTOCK','RETURN','DAMAGE','EXPIRY','LOST','SALE'),
			 action_time datetime default current_timestamp ,
             quantity bigint,
             agent_id bigint,
             foreign key(pro_id) references products(id),
             foreign key (agent_id) references users(id));

create table attributes(pro_id bigint,
                        type varchar(200) not null,
                        value varchar(200) not null,
                        foreign key (pro_id) references products(id))  ;

