<%@ page pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
<!DOCTYPE html>
<html>
<head>
<title>���[�U�[�o�^</title>

<%@include file="/CSS/RegistStyle.css"%>

</head>
<body>
    <h1 id="title">���[�U�[�o�^</h1>

    <form method='post'>
    ���[�U�[��<input type='text' name='name'><br>
    ���[���A�h���X<input id='mailAddress' type='text' name='mail'><div class="circle" id="mailCircleID"></div><div class="circle2" id="mailCircle2ID"></div><br>
    �p�X���[�h<input type='text' id='inputpass' name='pass' maxlength="20"><div class="circle" id="circleID"></div><div class="circle2" id="circle2ID"></div><br>
    �p�X���[�h(�ē���)<input type='text' id='inputagainpass' name='againpass'><br>
    <button onclick="fun1();">�o�^</button>
    </form>
<script type="text/javascript">
	var mail = document.getElementById("mailAddress");
	var pass = document.getElementById("inputpass");
	var passagain = document.getElementById("inputagainpass");
//���K�\��
	const regis =/^[A-Z]([a-zA-Z0-9]){7,19}$/;
	const reg =/^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}.[A-Za-z0-9]{1,}$/;
//CSS�����
	const circle=document.getElementById("circleID");
	const circle2=document.getElementById("circle2ID");
	const mailCircle=document.getElementById("mailCircleID");
	const mailCircle2=document.getElementById("mailCircle2ID");

	mailCircle.style.display="none";
	mailCircle2.style.display="none";
	circle.style.display="none";
	circle2.style.display="none";
//���K�\�������[���A�h���X�ɐݒ�
	mail.addEventListener("input",function(){
	if(reg.test(mail.value)){
		console.log("ok");
		mailCircle2.style.display="none";
		mailCircle.style.display ="inline-block";
	}else{
		console.log("no");
		mailCircle2.style.display="none";
		mailCircle.style.display ="inline-block";
	}
	});
//���K�\�����p�X���[�h�ɐݒ�
	pass.addEventListener("input",function(){
	if(regis.test(pass.value)){
		console.log("ok");
		circle2.style.display="none";
		circle.style.display ="inline-block";
	}else{
		console.log("no");
		circle2.style.display="none";
		circle.style.display ="inline-block";
	}
	});
//password�̃`�[�N
	passagain.addEventListener("input",function(){
		if(passagain.value === pass.value){
			console.log("ok");
		}else{
			console.log("no");
		}
	});

</script>

</body>
</html>
