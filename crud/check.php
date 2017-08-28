<?php
    $user=$_POST['user'];
    $pass=$_POST['pass'];
    $conn = mysql_connect("localhost","root","");
    mysql_select_db("crud_ar");

    $query = "SELECT * FROM users WHERE nama = '$user' AND sandi='$pass'";
    $result = mysql_query($query) or die("Unable to verify user because : " . mysql_error());
      if (mysql_num_rows($result) == 1){
          echo 1;
      }
   else {
         echo 0;
      }
?> 