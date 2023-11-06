use master
go

CREATE DATABASE DB_Sock1
go

use DB_Sock1
go

Create table Category
(
	id uniqueidentifier primary key default NEWID()
	,code nvarchar(100) not null
	,[name] nvarchar(255) not null
	,[status] bit default 1
)

Create table Producer
(
	id uniqueidentifier primary key default NEWID()
	,code nvarchar(100) not null
	,[name] nvarchar(255) not null
	,[status] bit default 1
)

Create table Meterial
(
	id uniqueidentifier primary key default NEWID()
	,code nvarchar(100) not null
	,[name] nvarchar(255) not null
	,[status] bit default 1
)


Create table Size
(
	id uniqueidentifier primary key default NEWID()
	,code nvarchar(100) not null
	,[name] nvarchar(255) not null
	,[status] bit default 1
)

Create table Sock
(
	id uniqueidentifier primary key default NEWID()
	,code nvarchar(100) not null
	,[name] nvarchar(255) not null
	,id_category uniqueidentifier
	,id_producer uniqueidentifier
	,id_meterial uniqueidentifier
	,id_size uniqueidentifier
	,outstanding bit default 0
	,constraint fk_sock_category foreign key(id_category) references Category(id)
	,constraint fk_sock_producer foreign key(id_producer) references Producer(id)
	,constraint fk_sock_meterial foreign key(id_meterial) references Meterial(id)
	,constraint fk_sock_size foreign key(id_size) references Size(id)
	,[status] bit default 1
)

Create table Discount
(
	id uniqueidentifier primary key default NEWID()
	,coupon_code nvarchar(255) not null
	,[name] nvarchar(255) not null
	,[description] nvarchar(max)
	,valid_from date not null
	,valid_until date not null
	,cash_discount money
	,percent_discount float
	,discount_type nvarchar(50)
	,[status] bit default 1,
)

Create table Color
(
	id uniqueidentifier primary key default NEWID()
	,code nvarchar(100) not null
	,[name] nvarchar(255) not null
	,[status] bit default 1
)


Create table Sock_Detail
(
	id uniqueidentifier primary key default NEWID()
	,quantity int not null
	,unit_base_price money not null
	,id_discount uniqueidentifier
	,[description] nvarchar(max)
	,id_sock uniqueidentifier
	,id_color uniqueidentifier
	,[path] nvarchar(max) not null
	,[status] bit default 1
	,constraint fk_sockDeTail_sock foreign key(id_sock) references Sock(id)
	,constraint fk_sockDeTail_color foreign key(id_color) references Color(id)
	,constraint fk_sockDetail_discount foreign key(id_discount) references Discount(id)
)


Create table Blog
(
	id uniqueidentifier primary key default NEWID()
	,title nvarchar(255) not null
	,[path] nvarchar(max) not null
	,date_create date
	,date_update date
	,content nvarchar(max) not null
	,[status] bit default 1
)

Create table Sock_Blog
(
	id uniqueidentifier primary key default NEWID()
	,id_blog uniqueidentifier
	,id_sock uniqueidentifier
	,[status] bit default 1
	,constraint fk_sockDetailBlog_Blog foreign key(id_blog) references Blog(id)
	,constraint fk_sockDetailBlog_sock foreign key(id_sock) references Sock(id)
)

Create table Customer
(
	updated_at datetime
	,deleted bit default 0
	,id uniqueidentifier primary key default NEWID()
	,[name] nvarchar(255) not null
	,email nvarchar(255) not null
	,phone nvarchar(20) not null
	,birthday date not null
	,wardcode NVARCHAR(20) not null
	,districtcode int not null
	,address NVARCHAR(100) not null
	,[status] bit default 1,
)

Create table Customer_Address
(
	updated_at datetime
		,deleted bit default 0
		,id uniqueidentifier primary key default NEWID()
		,[address] nvarchar(255) not null
		,city nvarchar(255) not null
		,country nvarchar(255) not null
		,id_customer uniqueidentifier
			,wardcode NVARCHAR(20) not null
	,districtcode int not null
	,address NVARCHAR(100) not null
		,constraint fk_customerAddress_customer foreign key(id_customer) references Customer(id)
)

Create table Account
(
	updated_at datetime
		,deleted bit default 0
		,id uniqueidentifier primary key default NEWID()
		,email nvarchar(255) not null
		,[password] nvarchar(max) not null
		,[status] bit default 1
		,id_customer uniqueidentifier
		,constraint fk_account_customer foreign key(id_customer) references Customer(id)
)

Create table Cart
(
	updated_at datetime
		,deleted bit default 0
		,id uniqueidentifier primary key default NEWID()
		,id_account uniqueidentifier
		,date_create date not null
		,note nvarchar(max)
		,[status] nvarchar(100) not null
		,constraint fk_cart_account foreign key(id_account) references Account(id)
)

Create table Cart_Detail
(
	updated_at datetime
		,deleted bit default 0
		,id_cart uniqueidentifier
		,id_sock_detail uniqueidentifier
		,quantity int not null
		,note nvarchar(max)
		,[status] nvarchar(100) not null
		,primary key(id_cart, id_sock_detail)
		,constraint fk_cartDetail_cart foreign key(id_cart) references Cart(id)
		,constraint fk_cartDetail_sockDetail foreign key(id_sock_detail) references Sock_Detail(id)
)

Create table Pay_Method
(
	updated_at datetime
		,deleted bit default 0
		,id uniqueidentifier primary key default NEWID()
		,[name] nvarchar(255)
		,[status] bit default 1
)

Create table [Role]
(
	id uniqueidentifier primary key default NEWID()
		,[name] nvarchar(255)
		,[permissions] nvarchar(255)
		,roles nvarchar(255)
		,[status] bit default 1
)

Create table Employee
(
	id uniqueidentifier primary key default NEWID()
		,[name] nvarchar(255) not null
		,birthday nvarchar(255) not null
		,phone nvarchar(20) not null
		,[address] nvarchar(255) not null
		,[email] nvarchar(255) not null
		,[password] nvarchar(max) not null
		,id_role uniqueidentifier
		,[status] bit default 1
			,wardcode NVARCHAR(20) not null
	,districtcode int not null
	,address NVARCHAR(100) not null
		,constraint fk_employee_role foreign key(id_role) references [Role](id)
)

Create table Bill
(
	updated_at datetime
		,deleted bit default 0
		,id uniqueidentifier primary key default NEWID()
		,date_create date not null
		,id_employee uniqueidentifier
		,date_payment date not null
		,id_customer uniqueidentifier
		,note nvarchar(max)
		,id_pay_method uniqueidentifier
		,[status] nvarchar(100) not null
		,constraint fk_bill_employee foreign key(id_employee) references Employee(id)
		,constraint fk_bill_customer foreign key(id_customer) references Customer(id)
		,constraint fk_bill_payMethod foreign key(id_pay_method) references Pay_Method(id)
)

Create table Bill_Detail
(
	updated_at datetime
		,deleted bit default 0
		,id_bill uniqueidentifier
		,id_sock_detail uniqueidentifier
		,quantity int not null
		,price money not null
		,[node] nvarchar(max)
		,[status] nvarchar(100) not null
		,primary key(id_bill, id_sock_detail)
		,constraint fk_billDetail_bill foreign key(id_bill) references Bill(id)
		,constraint fk_billDetail_sockDetail foreign key(id_sock_detail) references Sock_Detail(id)
)
