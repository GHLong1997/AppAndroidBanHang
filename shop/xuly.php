	<?php
	include_once("config.php");
		$ham = $_POST["ham"];


		switch ($ham) {
			case 'DangKyTaiKhoan':
				$ham();
				break;

			case 'DangNhapTaiKhoan':
				$ham();
				break;

			case 'LayDanhSachLoaiSPCha':
				$ham();
				break;
			case 'LayDanhSachLoaiSPCon':
				$ham();
				break;
			case 'LayDanhSachThuongHieu':
				$ham();
				break;
			
			case 'LayDanhSachKhuyenMai':
				$ham();
				break;

			case 'LayDanhSachSanPhamPhoBien':
				$ham();
				break;

			case 'LayDanhSachSanPhamMoiVe':
				$ham();
				break;

			case 'LayKhoangGiaTienTopSanPhamBanChay':
				$ham();
				break;
			case 'LayTopDienThoaiPhoBien':
				$ham();
				break;
			case 'LaySanPhamTheoDanhMucDienThoai':
				$ham();
				break;
			case 'LayChiTietSanPham':
				$ham();
				break;
			case 'ThemDanhGia':
				$ham();
				break;
			case 'LayDanhSachDanhGia':
				$ham();
				break;
			case 'ThemHoaDon':
				$ham();
				break;
			
			
		}

		function DangKyTaiKhoan(){
			global $conn;
			if(isset($_POST["tendangnhap"]) || isset($_POST["matkhau"]) || isset($_POST["sodt"])|| isset($_POST["email"])  ||isset($_POST["maloainv"])){

				$tendangnhap = $_POST["tendangnhap"];
				$matkhau = $_POST["matkhau"];
				$sodt = $_POST["sodt"];
				$email = $_POST["email"];
			
				$maloainv = $_POST["maloainv"];
			}
			
			$truyvan = "INSERT INTO  nhanvien(TENDANGNHAP,MATKHAU,SODT,EMAIL,MALOAINV) VALUES('".$tendangnhap."', '".$matkhau."', '".$sodt."', '".$email."', '".$maloainv."')";
			if(mysqli_query($conn,$truyvan)){
				echo "{ ketqua : true }";
			}else{
				echo "{ ketqua : false }";
			}
			mysqli_close($conn);
			
		}

		function DangNhapTaiKhoan(){
			global $conn;
				if(isset($_POST["tendangnhap"]) || isset($_POST["matkhau"]) || isset($_POST["sodt"])|| isset($_POST["email"]) ){

				$tendangnhap = $_POST["tendangnhap"];
				$matkhau = $_POST["matkhau"];
				$sodt = $_POST["sodt"];
				$email = $_POST["email"];

			}

			$truyvan = "SELECT * FROM nhanvien Where TENDANGNHAP = '".$tendangnhap."' AND MATKHAU = '".$matkhau."' OR EMAIL = '".$email."' AND MATKHAU = '".$matkhau."' OR SODT = '".$sodt."' AND  MATKHAU =  '".$matkhau."' OR SODT = '".$sodt."'  ";
			$ketqua = mysqli_query($conn,$truyvan);
			
			if(mysqli_num_rows($ketqua)>=1){
				$dong = mysqli_fetch_array($ketqua);
				echo "{ ketqua : true, tendangnhap : \"".$dong["TENDANGNHAP"]."\"}";

			}else{

				echo "{ ketqua: false }"; 
			}
			mysqli_close($conn);
		
		}

		function LayDanhSachLoaiSPCha(){
			global $conn;

			$truyvan = "SELECT * FROM  loaisanpham  where  MALOAICHA = 0";
			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo  "\"DANHSACHLOAISANPHAMCHA\":";
			$chuoijson = array();
			if($ketqua){
				while ($dong = mysqli_fetch_array($ketqua)) {
				array_push($chuoijson, array("MALOAISP" => $dong["MALOAISP"], "TENLOAISP" => $dong["TENLOAISP"], "MALOAICHA" => $dong["MALOAICHA"], 
					"HINHLOAISP" => "http://".$_SERVER['SERVER_NAME']."/shop" .$dong["HINHLOAISP"]));
			}

			echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			}
			
			echo "}";
		}

		function LayDanhSachLoaiSPCon(){
			global $conn;
			if(isset($_POST["maloaisp"])){
				$maloaisp = $_POST["maloaisp"];
			}

			$truyvan = "SELECT * FROM  loaisanpham  where  MALOAISP = '".$maloaisp."' ";
			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo  "\"DANHSACHLOAISANPHAMCON\":";
			$chuoijson = array();
			if($ketqua){
				while ($dong = mysqli_fetch_array($ketqua)) {

					$truyvancon = "SELECT * FROM loaisanpham Where MALOAICHA =  '".$dong["MALOAISP"]."' ";
					$ketqua1 = mysqli_query($conn,$truyvancon);

					if($ketqua1){
						while ($dong1 = mysqli_fetch_array($ketqua1)) {
					
							array_push($chuoijson, array("MALOAISP" => $dong1["MALOAISP"], "TENLOAISP" => $dong1["TENLOAISP"], "MALOAICHA" => $dong1["MALOAICHA"], 
										"HINHLOAISP" => "http://".$_SERVER['SERVER_NAME']."/shop" .$dong1["HINHLOAISP"]));
					}
				}
			}
			echo  json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			echo "}";
		}
	}

		function LayDanhSachThuongHieu(){
			global $conn;

			$truyvan = "SELECT * FROM  thuonghieu LIMIT 20  ";
			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo  "\"DANHSACHTHUONGHIEU\":";
			$chuoijson = array();
			if($ketqua){
				while ($dong = mysqli_fetch_array($ketqua)) {
				array_push($chuoijson, array("MATHUONGHIEU" => $dong["MATHUONGHIEU"], "TENTHUONGHIEU" => $dong["TENTHUONGHIEU"],  
					"HINHTHUONGHIEU" => "http://".$_SERVER['SERVER_NAME']."/shop" .$dong["HINHTHUONGHIEU"],"LUOTMUA" => $dong["LUOTMUA"]));
			}

			echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			}
			
			echo "}";
		}

		function LayDanhSachKhuyenMai(){
			global $conn;
			$chuoijson = array();

		$ngayhientai = date("Y/m/d");//bat buoc bi datediff cua sql la ymd
			$truyvan = "SELECT * FROM khuyenmai km,loaisanpham lsp WHERE DATEDIFF(km.NGAYKETTHUC,'".$ngayhientai."') >=0 AND km.MALOAISP =lsp.MALOAISP";
			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo "\"DANHSACHKHUYENMAI\":";

			if($ketqua){

				while ($dong = mysqli_fetch_array($ketqua)) {

					$truyvanchitietkhuyenmai = "SELECT * FROM chitietkhuyenmai ctkm,sanpham sp WHERE ctkm.MASP = sp.MASP AND ctkm.MAKM = '".$dong["MAKM"]."'";
					$ketquakhuyenmai = mysqli_query($conn,$truyvanchitietkhuyenmai);
					//cứ mỗi khuyên mãi thì ta sẽ có 1 danh sach

					$chuoijsondanhsachsanpham = array();//nen de trong nay , neu de ngoai kia thi no se lay gia tri cua thang dau no add tiep khi qua thagn moi
					if($ketquakhuyenmai){
						while ($dongkhuyenmai = mysqli_fetch_array($ketquakhuyenmai)) {
							$chuoijsondanhsachsanpham[] = $dongkhuyenmai;
						}
					}

					array_push($chuoijson, array("MAKM" => $dong["MAKM"],"TENLOAISP" => $dong["TENLOAISP"],"TENKM" => $dong["TENKM"],"HINHKHUYENMAI" =>$dong["HINHKHUYENMAI"],"DANHSACHSANPHAM" => $chuoijsondanhsachsanpham));
				
				
				}

			}
			echo  json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			echo "}";
		}

		function LayDanhSachSanPhamPhoBien(){

			global $conn;
	
			$truyvan = "SELECT * FROM sanpham sp Where sp.MASP  ORDER BY LUOTMUA DESC  LIMIT 10,20 ";

			$ketqua = mysqli_query($conn,$truyvan);
	
			echo "{";
			echo "\"DANHSACHSANPHAMPHOBIEN\":";
			$chuoijson = array();
			if($ketqua) {

				while ($dong = mysqli_fetch_array($ketqua)) {
						array_push($chuoijson,array("MASP" => $dong["MASP"], "TENSP" => $dong["TENSP"], "SOLUONG" => $dong["SOLUONG"],"GIA" => $dong["GIA"],"ANHLON" => "http://".$_SERVER['SERVER_NAME']."/shop".$dong["ANHLON"],"LUOTMUA" => $dong["LUOTMUA"]));
					}

			}

		
			echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			echo "}";

		}

			function LayDanhSachSanPhamMoiVe(){
				global $conn;
				
			   	$truyvan = "SELECT * FROM  sanpham ORDER BY MASP DESC LIMIT 20  "; //bơi vi cai MASP no phát sinh động cho nên khi t them mới sp thi no se tang them 1 
			   	$ketqua = mysqli_query($conn,$truyvan);//lấy đc database
			   	$chuoijson = array();
			   	echo "{";
			   	echo "\"DANHSACHSANPHAMMOIVE\":";
			   	if($ketqua){
			   		while($dong = mysqli_fetch_array($ketqua)){
			   		                                                                                                                                             //chinh la localhost
			   		//làm thể này đẻ cho nó trung cach parse giong may thang kia, khỏe hơn
			   		array_push($chuoijson, array("MASP"=>  $dong["MASP"],"TENSP" => $dong["TENSP"],"GIA" => $dong["GIA"], "ANHLON" =>"http://".$_SERVER['SERVER_NAME']."/shop" .$dong["ANHLON"],"LUOTMUA" => $dong["LUOTMUA"],"SOLUONG" => $dong["SOLUONG"]));//cách 2
			   	}
			   	echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			   	}
			   
			   	echo "}";

		}

			function LayKhoangGiaTienTopSanPhamBanChay(){
				global $conn;

			   	$truyvan = "SELECT * FROM  sanpham WHERE TENSP like '%Điện thoại%' ORDER BY LUOTMUA DESC,GIA ASC      "; //bơi vi cai MASP no phát sinh động cho nên khi t them mới sp thi no se tang them 1 
			   	$ketqua = mysqli_query($conn,$truyvan);//lấy đc database
			   	$chuoijson = array();
			   	echo "{";
			   	echo "\"KHOANGGIATIENTOPSANPHAMBANCHAY\":";
			   	$temp=100000;
			   	$t=1500000;

			   	if($ketqua){
			   		while($dong = mysqli_fetch_array($ketqua)){
			   		
			   		if($dong["GIA"] >$temp){
			   		
			   			array_push($chuoijson, array("MASP"=>  $dong["MASP"],"TENSP" => $dong["TENSP"],"GIA" => $dong["GIA"], "ANHLON" =>"http://".$_SERVER['SERVER_NAME']."/shop" .$dong["ANHLON"],"LUOTMUA" => $dong["LUOTMUA"],"SOLUONG" => $dong["SOLUONG"]));//cách 2
			   			$temp=$t;
			   			$t*=2;
			   			if($dong["GIA"]>60000000){

			   				array_push($chuoijson, array("MASP"=>  $dong["MASP"],"TENSP" => $dong["TENSP"],"GIA" => $dong["GIA"], "ANHLON" =>"http://".$_SERVER['SERVER_NAME']."/shop" .$dong["ANHLON"],"LUOTMUA" => $dong["LUOTMUA"],"SOLUONG" => $dong["SOLUONG"]));//cách 2
			   				break;
			   			}
			   		}
			   		
			   	}
			   	echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			   	}
			   
			   	echo "}";

		}

		function LayTopDienThoaiPhoBien(){
				global $conn;

				if(isset($_POST["maloaisp"])|| isset($_POST["limit"])){
					$maloaisp = $_POST["maloaisp"];
					$limit = $_POST["limit"];

				}

			$truyvan = "SELECT * FROM loaisanpham Where MALOAICHA =  '".$maloaisp."' ";
			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo  "\"DANHSACHTOPDIENTHOAIPHOBIEN\":";
			$chuoijson = array();
			
				if($ketqua){
						while ($dong = mysqli_fetch_array($ketqua)) {
							$truyvan1 = "SELECT * FROM sanpham sp  Where MALOAISP =  '".$dong["MALOAISP"]."' ORDER BY LUOTMUA DESC LIMIT ".$limit.",2 ";
							$ketqua1 = mysqli_query($conn,$truyvan1);
							
							if($ketqua1){
								while ($dong1 = mysqli_fetch_array($ketqua1)) {

									$truyvan2 = "SELECT * FROM danhgia where danhgia.MASP = '".$dong1["MASP"]."'";
									$ketqua2 = mysqli_query($conn,$truyvan2);
									$chuoijsondanhgia = array();
									if($ketqua2){
										while ($dong2 = mysqli_fetch_array($ketqua2)) {
											array_push($chuoijsondanhgia, array("SOSAO" => $dong2["SOSAO"], "NOIDUNG" => $dong2["NOIDUNG"]));
										}
									}
									array_push($chuoijson, array("MASP"=>  $dong1["MASP"],"TENSP" => $dong1["TENSP"],"GIA" => $dong1["GIA"], "ANHLON" =>"http://".$_SERVER['SERVER_NAME']."/shop" .$dong1["ANHLON"],"LUOTMUA" => $dong1["LUOTMUA"],"SOLUONG" => $dong1["SOLUONG"],"MALOAISP" => $dong1["MALOAISP"],"DANHSACHDANHGIA"=> $chuoijsondanhgia));//cách 2


								}
							}
					}
				}
			echo  json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			echo "}";

		}

			function LaySanPhamTheoDanhMucDienThoai(){
				global $conn;

				if(isset($_POST["maloaisp"])){
					$maloaisp = $_POST["maloaisp"];
				}

			$chuoijson = array();
			$truyvan = "SELECT * FROM sanpham Where MALOAISP = '".$maloaisp."' ORDER BY LUOTMUA DESC LIMIT 20 ";
			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo  "\"DANHSACHSANPHAMTHEODANHMUCDIENTHOAI\":";
				if($ketqua){
						while ($dong1 = mysqli_fetch_array($ketqua)) {

								$truyvan2 = "SELECT * FROM danhgia where danhgia.MASP = '".$dong1["MASP"]."'";
									$ketqua2 = mysqli_query($conn,$truyvan2);
									$chuoijsondanhgia = array();
									if($ketqua2){
										while ($dong2 = mysqli_fetch_array($ketqua2)) {
											array_push($chuoijsondanhgia, array("SOSAO" => $dong2["SOSAO"], "NOIDUNG" => $dong2["NOIDUNG"]));
										}
									}
										array_push($chuoijson, array("MASP"=>  $dong1["MASP"],"TENSP" => $dong1["TENSP"],"GIA" => $dong1["GIA"], "ANHLON" =>"http://".$_SERVER['SERVER_NAME']."/shop" .$dong1["ANHLON"],"ANHNHO" => $dong1["ANHNHO"],"LUOTMUA" => $dong1["LUOTMUA"],"SOLUONG" => $dong1["SOLUONG"],"MALOAISP" => $dong1["MALOAISP"],"DANHSACHDANHGIA" => $chuoijsondanhgia));//cách 2
									
						}
				}

			echo  json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			echo "}";
		}

			function LayChiTietSanPham(){
				global $conn;

				if(isset($_POST["masp"])){
					$masp = $_POST["masp"];
				}

			$chuoijson = array();
			$truyvan = "SELECT sp.MASP,TENSP,GIA,ANHLON,ANHNHO,LUOTMUA,SOLUONG,MALOAISP,TENSHOP,THONGTIN,sp.MASHOP,LUOTTHICH FROM sanpham sp, shop  s Where sp.MASP = '".$masp."' AND sp.MASHOP = s.MASHOP";
			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo  "\"LAYCHITIETSANPHAM\":";

			$chuoijson1 = array();
			$truyvan1 = "SELECT * FROM chitietsanpham ctsp  Where ctsp.MASP = '".$masp."'";
			$ketqua1 = mysqli_query($conn,$truyvan1);

				if($ketqua1){
						while ($dong1 = mysqli_fetch_array($ketqua1)) {
										array_push($chuoijson1, array("TENCHITIET" => $dong1["TENCHITIET"], "GIATRI" => $dong1["GIATRI"]));//cách 2
									
						}
				}

				if($ketqua){
						while ($dong = mysqli_fetch_array($ketqua)) {
										array_push($chuoijson, array("MASP"=>  $dong["MASP"],"TENSP" => $dong["TENSP"],"GIA" => $dong["GIA"], "ANHLON" =>"http://".$_SERVER['SERVER_NAME']."/shop" .$dong["ANHLON"],"ANHNHO" => $dong["ANHNHO"],"LUOTMUA" => $dong["LUOTMUA"],"SOLUONG" => $dong["SOLUONG"],"MALOAISP" => $dong["MALOAISP"],"TENSHOP" => $dong["TENSHOP"],"MASHOP"=> $dong["MASHOP"],"LUOTTHICH"=>$dong["LUOTTHICH"],"THONGTIN" => $dong["THONGTIN"], "CHITIETSANPHAM" => $chuoijson1));//cách 2
									
						}
				}

			echo  json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			echo "}";
		}

		function ThemDanhGia(){

			global $conn;
			if(isset($_POST["madg"]) || isset($_POST["masp"]) || isset($_POST["tendangnhap"])|| isset($_POST["hinhdangnhap"]) || isset($_POST["sosao"]) || isset($_POST["noidung"]) || isset($_POST["tenthietbi"])){
				$madg = $_POST["madg"];
				$masp = $_POST["masp"];
				$tendangnhap = $_POST["tendangnhap"];
				$hinhdangnhap = $_POST["hinhdangnhap"];
				$sosao = $_POST["sosao"];
				$noidung = $_POST["noidung"];
				$tenthietbi = $_POST["tenthietbi"];
			}

			$ngaydanhgia = date("d/m/Y");
			$truyvan = "INSERT INTO  danhgia(MADG,MASP,TENDANGNHAP,HINHDANGNHAP,SOSAO,NOIDUNG,TENTHIETBI,NGAYDANHGIA) VALUES('".$madg."','".$masp."','".$tendangnhap."','".$hinhdangnhap."','".$sosao."','".$noidung."','".$tenthietbi."','".$ngaydanhgia."');";
			$ketqua = mysqli_query($conn,$truyvan);
			if($ketqua){
				echo "{ketqua: true}";
			}else{
				echo "{ketqua: false}";
			}

		}

		function LayDanhSachDanhGia(){

			global $conn;	
			if(isset($_POST["masp"])){
				$masp = $_POST["masp"];
				
			}

			$chuoijson = array();
			$truyvan = "SELECT * FROM danhgia WHERE MASP = '".$masp."' LIMIT 3 ";
			$ketqua = mysqli_query($conn,$truyvan);

			echo "{";
			echo "\"DANHSACHDANHGIA\":";
			if($ketqua){
              while ($dong= mysqli_fetch_array($ketqua)) {
              	array_push($chuoijson, array("MADG" => $dong["MADG"], "MASP" => $dong["MASP"], "TENDANGNHAP" => $dong["TENDANGNHAP"], "HINHDANGNHAP"=> $dong["HINHDANGNHAP"],"SOSAO" => $dong["SOSAO"],"NOIDUNG"=> $dong["NOIDUNG"],"TENTHIETBI" => $dong["TENTHIETBI"],"NGAYDANHGIA" => $dong["NGAYDANHGIA"]));
              }

			}

			echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
			echo "}";
		}

		function ThemHoaDon(){
			global $conn;   //danhsachsanpham la 1 chuoi json
			if(isset($_POST["danhsachsanpham"]) || isset($_POST["tennguoinhan"]) || isset($_POST["sodt"]) || isset($_POST["diachi"])){

					$danhsachsanpham = $_POST["danhsachsanpham"];
					$tennguoinhan = $_POST["tennguoinhan"];
					$sodt = $_POST["sodt"];
					$diachi = $_POST["diachi"];
			
					

			}

			$ngayhientai = date("d/m/Y");//lay ngay hien tai, qua ben configkhai bao, để dieu chỉnh ngày thi phai codate_modìy, thằng ngayhientai là 1 kiểu string
			$ngaygiao = date_create(date("d-m-Y"), timezone_open("Asia/Ho_Chi_Minh"));
            $ngaygiao = date_modify($ngaygiao, "+3 days");
            $ngaygiao = date_format($ngaygiao, "d/m/Y") ;

			$trangthai = "Chờ kiểm duyệt";


			$truyvan = "INSERT INTO hoadon (NGAYMUA,NGAYGIAO,TRANGTHAI,TENNGUOINHAN,SODT,DIACHI) VALUES('".$ngayhientai."', '".$ngaygiao."','".$trangthai."','".$tennguoinhan."', '".$sodt."', '".$diachi."') " ;

			$ketqua = mysqli_query($conn,$truyvan);

			if($ketqua){

				$mahd = mysqli_insert_id($conn);// tức là khi  thực hiện code thêm nào đó thì nó sẽ lấy 1 cái mã nào đó mới vừa tự động tăng sẽ gán vào thằng này(chi hd vs thagn nao tu dong tăng)
				$chuoijsonandroid = json_decode($danhsachsanpham);//chung ta dc 1 mangjsonroi
				$arraydanhsachsanpham = $chuoijsonandroid->DANHSACHSANPHAM;//lay ra thang DANHSACHSANPHAM ma chung ta tao ra o android
				
				for($i =0 ; $i< count($arraydanhsachsanpham); $i++){
						$jsonObject = $arraydanhsachsanpham[$i];//lay tung cot(boi vi no co dang key:values)

						$masp = $jsonObject->masp;//$jsonobject->$masp;: boi vi chung ta biet dc la thang android no se day len chuoi nhu the nay
						$soluong = $jsonObject->soluong;


						$truyvan1 = "INSERT INTO chitiethoadon (MAHD,MASP,SOLUONG) VALUES('".$mahd."', '".$masp."','".$soluong."')" ;
						$ketqua1 = mysqli_query($conn,$truyvan1);
						
					}
					echo "{ketqua:true}";

			}else{
				echo "{ketqua:false}" ;
			}

		}
	

	?>