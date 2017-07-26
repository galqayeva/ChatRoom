<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "android";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$name=$_POST["name"];
$username=$_POST["username"];
$mail=$_POST["mail"];
$password=$_POST["password"];


$sql = "INSERT INTO `loginuser`(`id`, `name`, `username`, `mail`, `password`) VALUES ('','".$name."','".$username."','".$mail."','".$password."')";
$response=array();
if ($conn->query($sql) === TRUE) {
	$code="success";

   array_push($response, array('code' =>$code));
	echo  json_encode($response);
} else {
   $message="failed";
	$code="fail";
	array_push($response, array('code' =>$code,'message'=>$message ));
echo  json_encode($response);
}

$conn->close();
?>