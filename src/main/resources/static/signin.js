import cartItems from './product'

cartItems[0]


let sub = document.getElementById('submit');

sub.addEventListener('click', function (e){
    
    e.preventDefault();
    let value = document.getElementById('uname').value;
    let pValue = document.getElementById('password').value;
    
    //Regex validation
    if (value==""){
        alert("User ID must be filled out");
        return false;
    }else if (pValue==""){
        alert("Password must be filled out");
        return false;
    }else {
        let idValue = document.createElement('p');
        idValue.innerText = "Inserted ID value is :" +value;
        document.body.appendChild(idValue);

        let pwValue = document.createElement('p');
        pwValue.innerText = "Inserted Password value is :" +pValue;
        document.body.appendChild(pwValue);
    }
})

