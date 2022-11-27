create database clotheshop;
use clotheshop;

insert into size values
(1,'S'),
(2,'M'),
(3,'L'),
(4,'XL');

insert into category values
(1,N'Nam'),
(2,N'Nữ'),
(3,N'Phụ kiện');

insert into productstyle (id_product_style,name_product_style,idcategory)values
(1,N'Áo thun',1),
(2,N'Áo thun',2),
(3,N'Quần short',1),
(4,N'Nón',3),
(5,N'Quần jean',2);
insert into color values
(1,N'Xanh dương'),
(2,N'Tím'),
(3,N'Vàng'),
(4,N'Xanh lá'),
(5,N'Xám'),
(6,N'Xanh rêu'),
(7,N'Đỏ'),
(8,N'Cam'),
(9,N'Đen'),
(10,N'Trắng');


insert into product
(id_product,name_product,price,description,create_date,idcategory,idproductstyle,product_photo) 
values
(1,N'PARADOX® ESSENTIAL EMBROIDERY TEE (Charcoal)',
420000,'Áo thun form rộng ESSENTIAL EMBROIDERY TEE là một sản phẩm áo thun basic cao cấp và chất lượng, được cho ra mắt với nhiều màu sắc vô cùng mới mẻ kết hợp hoạ tiết thêu xù logo thương hiệu Paradox một cách tinh tế. Sản phẩm đã được nghiên cứu và thử nghiệm trong một thời gian dài nhằm nâng cấp chất lượng lên một tầng cao mới so với những item trước đây. Hứa hẹn sẽ là một item bạn không thể bỏ qua cho tủ đồ của mình.',
'2022-08-30',1,1,'paradoxshirtcarol.png'),
(2,N'SIGNATURE',99000,'','2022-08-30',1,3,'paradoxshortgreen.png'),
(3,N'Áo thun Paradox ECSTASY TEE',244000,'Áo thun Artwork ECSTASY TEE là sản phẩm áo thun đen với thiết kế độc đáo và đầy ý nghĩa về môi trường dựa trên nền chất liệu áo phông Basic, vừa đem lại cho bạn cảm giác thoải mái vừa thể hiện cá tính riêng của bạn thông qua những hoạ tiết, hình vẽ do chính đội ngũ Designer của Paradox thiết kế.','2022-08-30',2,2,'paradoxgirlbl.jpg'),
(4,N'QUẦN JEANS NỮ',990000,'','2022-08-30',2,5,'jeangirlblue.jpg');



insert into productcolors values
(1,'paradoxshirtcarol.png',5,1),
(2,'paradoxshortgreen.png',6,2),
(3,'paradoxshortred.png',7,2),
(4,'paradoxshortorange.png',8,2),
(5,'paradoxgirlbl.jpg',9,3),
(6,'paradoxgirlwwhite.jpg',10,3),
(7,'paradoxshirtovil.png',6,1),
(8,'jeangirlblue.jpg',1,4),
(9,'jeangirlblack.jpg',9,4);

insert into productsizes values
(1,100,1,1),
(2,50,2,1),
(3,10,3,1),
(4,100,1,2),
(5,50,2,2),
(6,10,3,2),
(7,100,1,3),
(8,50,2,3),
(9,10,3,3),
(10,100,1,4),
(11,50,2,4),
(12,10,3,4),
(13,100,4,1),
(14,50,5,1),
(15,10,6,1),
(16,100,4,2),
(17,50,5,2),
(18,10,6,2),
(19,100,4,3),
(20,50,5,3),
(21,10,6,3),
(22,100,4,4),
(23,50,5,4),
(24,10,6,4),
(25,100,7,1),
(26,50,8,1),
(27,10,9,1),
(28,100,7,2),
(29,50,8,2),
(30,10,9,2),
(31,100,7,3),
(32,50,8,3),
(33,10,9,3),
(34,100,7,4),
(35,50,8,4),
(36,10,9,4);

insert into account values
('phu2345','',N'Phú','12345','034949585','');

insert into role values
('admin',N'quản lý');

insert into authorities values
(1,'phu2345','admin');




