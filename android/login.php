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

$username=$_POST["username"];
$password=$_POST["password"];


$sql = "SELECT `id`, `name`, `username`, `mail`, `password` FROM `loginuser` WHERE username='$username' && password='$password'";

$result = $conn->query($sql);

$response=array();

if ($result->num_rows > 0) {

    $row = $result->fetch_assoc();
	$username=$row["username"];
	$mail=$row["mail"];

	$code="Login Succesed";


	array_push($response, array('code' =>$code,'username'=>$username,'mail'=>$mail ));
echo  json_encode($response);

}
else{

	$message="failed";
	$code="Login Failed";
	array_push($response, array('code' =>$code,'message'=>$message ));
echo  json_encode($response);
}

$conn->close();

?>