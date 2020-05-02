const crypto = require('crypto');
const axios = require('axios');
const FormData = require('form-data');

const myToken = '';

dados = axios.get(`https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=${myToken}`)
    .then(dados => {
        let { data } = dados;
        data.decifrado = decrypt(data.cifrado, data.numero_casas);
        var shasum = crypto.createHash('sha1').update(data.decifrado).digest('hex');
        data.resumo_criptografico = shasum;

        const form = new FormData();
        form.append('answer', JSON.stringify(data), 'answer.json');
        console.log(data);

        axios.post(`https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=${myToken}`, form, {
            headers: form.getHeaders(),
        }).then(result => {
            console.log(result.data);
        });
    });

function encrypt(text, shift) {
    var result = "";
    for (let i = 0; i < text.length; i++) {
        let ch = text.charCodeAt(i);

        if (ch >= 65 && ch <= 90) {
            result += String.fromCharCode((ch - 65 + shift) % 26 + 65);
        } else if (ch >= 97 && ch <= 122) {
            result += String.fromCharCode((ch - 97 + shift) % 26 + 97);
        } else {
            result += text.charAt(i);
        }
    }
    return result;
}

function decrypt(text, shift) {
    var result = "";
    shift = (26 - shift) % 26;
    return encrypt(text, shift);
}