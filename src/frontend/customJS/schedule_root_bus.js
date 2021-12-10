function searchPubTransPathAJAX() {
    const appkey = 'xzm+dv1+LZESXRkacw+Wot/SwZU38z9Ubd7tPsAt1eg';
    var xhr = new XMLHttpRequest();
    var url = `https://api.odsay.com/v1/api/searchPubTransPath?SX=126.9027279&SY=37.5349277&EX=126.9145430&EY=37.5499421&apiKey=${appkey}`;
    console.log(url)
    xhr.open("GET", url, true);
    xhr.send();
    xhr.onreadystatechange = function() {

        if (xhr.readyState == 4 && xhr.status == 200) {
            console.log( xhr.responseText ); // <- xhr.responseText 로 결과를 가져올 수 있음
        }
    }
}
searchPubTransPathAJAX()