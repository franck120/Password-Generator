const zxcvbn = require("zxcvbn"); //Import de la bibliothèque node zxcvbn, utiliser ici pour tester la solidité du mot de passe
const password = process.argv[2]; //Récupère le mot de passe à tester
//Teste du mot de passe reçu
if (!password) {
    console.error("Mot de passe manquant");
    process.exit(1);
}
//zxcvbn retourne teste le mot de passe et retourne un score en fonction de la robustesse de celui ci 
const result = zxcvbn(password); 
process.stdout.write(String(result.score));