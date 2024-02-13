"use strict";
// const nomi = [...Array(18).keys()].map( e => `m_slideshow/SDG-icon-IT-RGB-${(e<10?"0":"")+e}.jpg` );
const slide = [
    {file:"SDG-icon-IT-RGB-00.jpg", testo:null},
    {file:"SDG-icon-IT-RGB-01.jpg", testo:"836 milioni di persone vivono ancora in povertà estrema. Circa una persona su cinque nelle regioni in via sviluppo vive con meno di 1,25 dollari al giorno"},
    {file:"SDG-icon-IT-RGB-02.jpg", testo:"Circa 795 milioni di persone nel mondo – ovvero una persona su nove – sono denutrite.La maggior parte delle persone che nel mondo soffre la fame vive in Paesi in via di sviluppo, dove il 12,9% della popolazione è denutrita."},
    {file:"SDG-icon-IT-RGB-03.jpg", testo:"Ogni giorno muoiono 17.000 bambini in meno rispetto al 1990; tuttavia, ogni anno continuano a morire più di sei milioni di bambini prima del compimento del quinto anno d’età. Dal 2000, i vaccini contro il morbillo hanno prevenuto quasi 15,6 milioni di morti."},
    {file:"SDG-icon-IT-RGB-04.jpg", testo:"L’iscrizione nelle scuole primarie nei Paesi in via di sviluppo ha raggiunto il 91%, ma 57 milioni di bambini ne sono ancora esclusi. Più della metà dei bambini non iscritti a scuola vive in Africa subsahariana"},
];

let indice = 0;
let box;
let infoAperte = false;
function mostraInfo(){
    infoAperte = true;
    let info = document.createElement("div");
    info.style.position = "fixed";
    info.innerText = slide[indice].testo;
    info.style.cssText = `
        position: absolute;
        min-height: 40%; width: 40%;
        right: 2vw; bottom: 2vh;
        background-color:rgba(255,255,255,0.92);
        padding:1rem; border: 3px solid #eecd00;
        font-size:5vh`;
    info.addEventListener("click", () => {document.querySelector("body").removeChild(info); infoAperte=false;} );
    document.querySelector("body").appendChild(info);
}

function cambiaSlide(){
    if(infoAperte==false){
        indice = (indice+1) % slide.length;
        console.log(indice);
        box.innerHTML = '';
        box.removeEventListener("click", mostraInfo);

        box.style.backgroundImage = "url(m_slideshow/"+slide[indice].file+")";

        let titolo = document.createElement("p");
        titolo.style.margin = "0";
        titolo.style.background = "#eecd00";
        titolo.innerText="agenda 2030";
        box.appendChild(titolo);

        if(slide[indice].testo){
            let info = document.createElement("p");
            info.style.margin = "0";
            info.innerText="click per info";
            info.style.background = "#eecd00";
            box.appendChild(info);
            box.addEventListener("click", mostraInfo);
        }
    }
}

function m_slideshow(id){
    box = document.getElementById(id); 

    // serve "+=" per mantene3re eventuali impostazioni fatte nella pagina pricipale
    // a meno di idee più articolate
    box.style.cssText += `
        color: blue; background-color: #444;
        text-align: center;
        background-repeat: no-repeat; background-position: center 95%;
        background-size: ${dimRif.width>dimRif.height ? "auto 75%" : "75% auto"};
    `;

    cambiaSlide();
    window.setInterval(cambiaSlide, 5000 );
}