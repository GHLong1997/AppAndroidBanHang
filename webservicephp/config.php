<?php
	define("DBSERVER","localhost"); 
	define("DBUSERNAME" ,"root");
	define("DBPASSWORD", "");
	define("DBNAME", "shop1");

	date_default_timezone_get("Asia/Ho_Chi_Minh");

	$conn = mysqli_connect(DBSERVER,DBUSERNAME,DBPASSWORD,DBNAME);
	$conn->set_charset("utf8");
	// if($conn-> connect_error){
	// 	die('Connect Error:' .$conn -> connect_error);
	// }


	
?>