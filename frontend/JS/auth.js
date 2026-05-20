function checkAuth(){
    const user = sessionStorage.getItem('user');
    if(!user)
    {
        window.location.href='../../FORMS/LoginPage/LoginAndCreatePage.html';
        return null;
    }
    return JSON.parse(user);
}

//check if Admin
function checkAdmin()
{
    const role = sessionStorage.getItem('role');
    if(!role || role !== 'ADMIN')
    {
        window.location.href='../../FORMS/LoginPage/LoginAndCreatePage.html';
        return false ;
    }
    return true;
}
 //logout
 function logout()
 {
    Swal.fire({
    title: 'Logout?',
        text: 'Are you sure you want to logout?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonColor: '#ff4d6a',
        cancelButtonColor: '#444',
        confirmButtonText: 'Yes, logout',
        cancelButtonText: 'Cancel',
        background: '#16161f',
        color: '#f0f0f8'
    }).then((result) => {
        if (result.isConfirmed) {
            sessionStorage.clear();
            window.location.href = '../../FORMS/LoginPage/LoginAndCreatePage.html';
        }
    });
 }

 // Run check immediately when page loads
document.addEventListener('DOMContentLoaded', () => {
    checkAuth();
});