let time: NodeJS.Timeout;

export const inactivityTimer = (logout : () => void) => {
    window.onload = resetTimer;
    // DOM Events
    document.onmousemove = resetTimer;
    document.onkeydown = resetTimer;

    function resetTimer() {
        clearTimeout(time);
        time = setTimeout(logout, 1000*60)
    }
};