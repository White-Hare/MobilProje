using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace MobilProjeBackend.Models
{
    public class MyUser
    {
        [Key] public long Id {get; set;}
        [MinLength(5), MaxLength(40), Required]public string Username { get; set; }
        [MinLength(5), MaxLength(40), Required]public string Password { get; set; }
        [NotMapped] public Profile Profile { get; set;}
        [ForeignKey("Profile")]public long ProfileId{get; set;}
    }
}
