<?php
	include("koneksi.php");
	//aktivasi global variable
	extract($_REQUEST, EXTR_OVERWRITE);
	
	if ($ct == "SEL_kd") {
		$sqlSEL = "SELECT DISTINCT kd FROM karyawan";
		$hasil = mysql_query($sqlSEL);
		$kirim = "";
		
		while ($data = mysql_fetch_array($hasil)) {
			$kd = stripcslashes($data['kd']);
			$kirim .= $kd. "#";
		}
		echo $kirim;
	} 
?>