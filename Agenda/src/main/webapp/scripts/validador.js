/**
 * Validação de formulario
* @Autor: Carlos Eduardo Cassimiro Paz
 */

function validar(){
	let Nome = frmContato.Nome.value;
	let Telefone = frmContato.Telefone.value;
	
	if(Nome === ""){
		alert("Nome vazio");
		frmContato.Nome.focus()
		return false;
	}else if(Telefone === ""){
		alert("Telefone vazio");
		frmContato.Telefone.focus()
		return false;
	}else{
		document.forms["frmContato"].submit()
	}
}