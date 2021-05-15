using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MobilProjeBackend.Models;

namespace MobilProjeBackend.Models
{
    public class AuthenticateResponse
    {     
        public long Id { get; set; }
        public string Username { get; set; }
        public long ProfileId { get; set; }
        public string Token { get; set; }


        public AuthenticateResponse(MyUser user, string token)
        {
            Id = user.Id;
            Username = user.Username;
            ProfileId = user.ProfileId;
            Token = token;
        }
    }
}
