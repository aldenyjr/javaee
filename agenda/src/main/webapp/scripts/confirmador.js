/**
 * Confirmacao de exclusao do contato
 * @author Aldeny Junior
 */

 const confirmar = (idcon) => {
	let resposta = confirm("Confirmar a exclusão deste contato?")
	
	if (resposta) {
		//alert(idcon)
		window.location.href = `delete?idcon=${idcon}`
	}
 }