<?php
mysql_connect("localhost","root","");
mysql_select_db("crud_ar"); //database name
switch($_GET['a']){
case "insert" :
                $id = $_GET['kd'];
                $nm = $_GET['nm'];
                $gaji = $_GET['gaji'];
                $des = $_GET['deskripsi'];
                $result = mysql_query("INSERT INTO karyawan(kd,nm,gaji,deskripsi,status) VALUES('$id','$nm','$gaji','$des','active')");
                if($result){
                                echo "berhasil insert data";
                }else{
                                echo "nm".$nm;
                }
break;
case "update":
                $kd = $_GET['kd'];
                $nm = $_GET['nm'];
                $gaji = $_GET['gaji'];
                $des = $_GET['des'];
                $result = mysql_query("UPDATE karyawan SET nm='$nm' , gaji='$gaji', deskripsi='$des' WHERE kd='$kd'");
                if($result){
                                echo "berhasil ubah data";
                }else{
                                echo "gagal ubah data";
                }
break;
case "delete":
                $id = $_GET['kd'];
                $result = mysql_query("DELETE FROM karyawan WHERE kd='$id'");
                if($result){
                                echo "berhasil hapus data";
                }else{
                                echo "gagal hapus data";
                }
break;
case "read":
                $kd=$_GET['kd'];
                /* Source code untuk Menampilkan Data */
                $q = mysql_query("SELECT * FROM karyawan where kd='$kd'");
                $v = '{"data" : [';
                while($r=mysql_fetch_array($q))
                {
                                $ob = array('"','<br>','</br>');
                                $v .='{"kd" : "'.$r['kd'].'",
                                "nm" : "'.str_replace($ob,' ',strip_tags($r["nm"])).'",
                                "gaji" : "'.str_replace($ob,' ',strip_tags($r["gaji"])).'",
                                "deskripsi" : "'.str_replace($ob,' ',strip_tags($r["deskripsi"])).'",
                                "status" : "'.str_replace($ob, ' ', strip_tags($r["status"])).'"}';
                }
                $v .= ']}';
                echo $v;
}
?>