/**
 * Validação de Formulario
 * @author Aldeny Junior
 */

 const validar = () => {
	 let nome = frmContato.nome.value
	 let fone = frmContato.fone.value
	 let email = frmContato.email.value	 
	 const regex = /\(\d{2}\) \d{5}-\d{4}/;
	 

	 	if(!nome){
			 frmContato.nome.focus();
			 frmContato.nome.style.outlineColor = "red";
			 document.querySelector(".obrigatorio1").innerText = "Este Campo é obrigatorio!"		 
			 return false;
		 
	 	} else if(!fone){
			 document.querySelector(".obrigatorio1").innerText = ""
			 frmContato.fone.focus()
			 frmContato.fone.style.outlineColor = "red";
			 document.querySelector(".obrigatorio2").innerText = "Este Campo é obrigatorio!"
			 return false;
		 
	 	} else if(!regex.test(fone)){
			frmContato.fone.focus()
		    frmContato.fone.style.outlineColor = "red";
		    document.querySelector(".obrigatorio2").innerText = "Formato Invalido! Ex: (99) 99999-9999"
		    return false
		    
		}  else {
		 
		 document.querySelectorAll("p").forEach(el => {
			 el.innerText = ""
		 })
		document.forms["frmContato"].submit()
	 }
 }
 
 const criaMascara = (parans) => {
	 
	 let inputFone = document.querySelector('.caixa2')
	 let maxLength = inputFone.maxLength
	 
	 const Mask = {
		 fone: inputFone.value.replace(/[^\d]/g, "").replace(/(\d{2})(\d{5})(\d{4})/, "($1) $2-$3")
	 }
	 
	 if(inputFone.value.length === maxLength){
		 document.querySelector('.caixa2').value = Mask[parans]
	 }
 }