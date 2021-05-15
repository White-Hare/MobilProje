using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Text;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Options;
using Microsoft.IdentityModel.Tokens;
using MobilProjeBackend.Data;
using MobilProjeBackend.Helpers;
using MobilProjeBackend.Models;

namespace MobilProjeBackend.Services
{
    public interface IUserService
    {
        AuthenticateResponse Authenticate(AuthenticationRequest model);
        IEnumerable<MyUser> GetAll();
        MyUser GetById(int id);
        Task<MyUser> RegisterUser(MyUser user);
    }

    public class UserService : IUserService
    {

        private readonly AppSettings _appSettings;
        private readonly UserContext _context;

        public UserService(IOptions<AppSettings> appSettings, UserContext context)
        {
            _appSettings = appSettings.Value;
            _context = context;
        }

        public AuthenticateResponse Authenticate(AuthenticationRequest model)
        {
            var user = _context.MyUsers.SingleOrDefault(x => x.Username == model.Username && x.Password == model.Password);
            if (user == null) return null;

            var token = GenerateJwtToken(user);
            return new AuthenticateResponse(user, token);
        }

        public IEnumerable<MyUser> GetAll()
        {
            return _context.MyUsers;
        }

        public MyUser GetById(int id)
        {
            return _context.MyUsers.FirstOrDefault(x => x.Id == id);
        }

        public async Task<MyUser> RegisterUser (MyUser user)
        {
            await  _context.MyUsers.AddAsync(user);
            await _context.SaveChangesAsync();

            return user;
        }

        private string GenerateJwtToken(MyUser user)
        {
            var tokenHandler = new JwtSecurityTokenHandler();
            var key = Encoding.ASCII.GetBytes(_appSettings.Secret);
            var tokenDescriptor = new SecurityTokenDescriptor
            {
                Subject = new ClaimsIdentity(new[] {new Claim("id", user.Id.ToString())}),
                Expires = DateTime.Now.AddHours(1),
                SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(key),
                    SecurityAlgorithms.HmacSha256Signature)
            };

            var token = tokenHandler.CreateToken(tokenDescriptor);
            return tokenHandler.WriteToken(token);
        }
    }
}
