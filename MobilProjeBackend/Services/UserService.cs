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
        Task<MyUser> RegisterUser(AuthenticationRequest request);
    }

    public class UserService : IUserService
    {

        private readonly AppSettings _appSettings;
        private readonly UserContext _userContext;
        private readonly BasicItemsContext _basicItemsContext;

        public UserService(IOptions<AppSettings> appSettings, UserContext userContext, BasicItemsContext basicItemsContext)
        {
            _appSettings = appSettings.Value;
            _userContext = userContext;
            _basicItemsContext = basicItemsContext;
        }

        public AuthenticateResponse Authenticate(AuthenticationRequest model)
        {
            var user = _userContext.MyUsers.SingleOrDefault(x => x.Username == model.Username && x.Password == model.Password);
            if (user == null) return null;

            var token = GenerateJwtToken(user);
            return new AuthenticateResponse(user, token);
        }

        public IEnumerable<MyUser> GetAll()
        {
            return _userContext.MyUsers;
        }

        public MyUser GetById(int id)
        {
            return _userContext.MyUsers.FirstOrDefault(x => x.Id == id);
        }

        public async Task<MyUser> RegisterUser (AuthenticationRequest request)
        {
            Profile profile = new Profile{BestScore = 0, TotalCorrectAnswers = 0, TotalWrongAnswers = 0};

            await  _basicItemsContext.Profiles.AddAsync(profile);
            await _basicItemsContext.SaveChangesAsync();

            MyUser user = new MyUser
            {
                Username = request.Username,
                Password = request.Password,
                ProfileId = profile.Id,
                Profile = profile,
            };

            await  _userContext.MyUsers.AddAsync(user);
            await _userContext.SaveChangesAsync();

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
