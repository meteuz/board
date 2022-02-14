
const arrDayStr = ['일', '월', '화', '수', '목', '금', '토'];
let timeout = '';
let timeVal = '';

function getTime(){
    const currentDate = new Date();

    const yearStr = currentDate.getFullYear() + '년';
    const monthStr = `${(currentDate.getMonth() + 1) < 10 ? `0${(currentDate.getMonth() + 1)}` : (currentDate.getMonth() + 1)}월`;
    const dateStr = `${currentDate.getDate() < 10 ? `0${currentDate.getDate()}` : currentDate.getDate()}일`;
    const dayStr = '[' + arrDayStr[currentDate.getDay()] + ']';
    const currentDateStr = yearStr + ' ' + monthStr + ' ' + dateStr + ' ' + dayStr;

    let hour = currentDate.getHours();
    const minutes = currentDate.getMinutes();
    const seconds = currentDate.getSeconds();

    const ampm = hour >= 12 ? '오후' : '오전';
    hour = hour % 12;
    hour = hour ? hour : 12;

    const timeStr = `${ampm} ${hour<10 ? `0${hour}`:hour}:${minutes<10 ? `0${minutes}`:minutes}:${seconds<10 ? `0${seconds}`:seconds}`;

    const dateAllStr = `${currentDateStr} ${timeStr}`;

    $('#currentDate').text(dateAllStr);
    // $('#currentTime').text(timeStr);

}

function init() {
    timeVal = setInterval(getTime, 1000);
}

$(function () {

    $(window).on('beforeunload', function() {
        clearTimeout(timeout);
        clearTimeout(timeVal);
    });

    getTime();
    init();
});