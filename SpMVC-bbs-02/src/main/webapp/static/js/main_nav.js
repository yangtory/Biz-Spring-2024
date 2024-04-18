// main-nav 각 tag className 을 키로하는 객체 선언
const NAV_INDEX = {
    home: { url: '/' },
    notice: { url: '/bbs/notice' },
    bbs: { url: '/bbs/free' },
};
document.addEventListener('DOMContentLoaded', () => {
    const mainNav = document.querySelector('nav.main');
    const navItems = mainNav.querySelectorAll('LI');

    mainNav.addEventListener('click', (e) => {
        const target = e.target;
        if (target.tagName === 'LI') {
            // 모든 nav.main li tag 에 active 라는 class 를 제거하기
            navItems.forEach((item) => {
                item.classList.remove('active');
            });
            target.classList.add('active');

            const className = target.classList[0];
            const url = NAV_INDEX[className].url;
            document.location.href = url;
        }
    });
});
