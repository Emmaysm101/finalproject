window.onload = function() {
        let result = 0;
        let length = document.getElementsByClassName('subTotal').length;
        for (let i=0; i<length; i++) {
            try {
                let temp = document.getElementsByClassName("subTotal")[i].innerHTML;
                result += parseFloat(temp)
            }
            catch (err) {
                break;
            }
        }
        document.getElementById('total').innerHTML = "Sub Total: $ " + result;
        let tax = result * 0.0625;
        let taxToShow = tax.toFixed(2);
        document.getElementById('tax').innerHTML = "Tax: $ " + taxToShow;
        let afterTax = result + tax;
        afterTax = afterTax.toFixed(2);
        document.getElementById('afterTax').innerHTML = "Total: $ " + afterTax;
    }



