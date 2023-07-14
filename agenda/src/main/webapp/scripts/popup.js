const modal = document.querySelector(".modal-container");

const openModal = (idcon, nomecontato) => {

	modal.classList.add("active");
	console.log(idcon, nomecontato);
	document.querySelector('.nomecontato').innerText = `${nomecontato}`;
	
	document.querySelector('.btnOK').addEventListener('click', () => {		
		window.location.href = `delete?idcon=${idcon}`
	})
};

const closeModal = () => {
	modal.classList.remove("active");
};
