using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using MobilProjeBackend.Models;
using MobilProjeBackend.Services;

namespace MobilProjeBackend.Controllers
{
    [ApiController, Route("[controller]")]
    public class UsersController : ControllerBase
    {
        private IUserService _userService;

        public UsersController(IUserService userService)
        {
            _userService = userService;
        }

        [HttpPost("Authenticate")]
        public IActionResult Authenticate(AuthenticationRequest model)
        {
            var response = _userService.Authenticate(model);

            if (response == null)
                return BadRequest(new {message = "Username or password is incorrect"});

            return Ok(response);
        }

        [HttpPost("Register")]//Fix it
        public async Task<ActionResult<MyUser>> Register(MyUser user)
        {
            await _userService.RegisterUser(user);

            return CreatedAtAction("GetById", new { id = user.Id }, user);
        }

        /*
        [Authorize]
        [HttpGet]
        public IActionResult GetAll()
        {
            var users = _userService.GetAll();
            return Ok(users);
        }
        */

    }
}
