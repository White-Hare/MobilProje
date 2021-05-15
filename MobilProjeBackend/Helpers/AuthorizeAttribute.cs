using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using MobilProjeBackend.Models;


namespace MobilProjeBackend.Helpers
{
    public class AuthorizeAttribute : Attribute, IAuthorizationFilter
    {
        public void OnAuthorization(AuthorizationFilterContext context)
        {
            var user = (MyUser) context.HttpContext.Items["User"];
            if (user == null)
                context.Result = new JsonResult(new {message = "Unauthorized"})
                    {StatusCode = StatusCodes.Status401Unauthorized};
        }
    }
}
