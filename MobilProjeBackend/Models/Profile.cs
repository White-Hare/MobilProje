using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace MobilProjeBackend.Models
{
    public class Profile
    {
        [Key]public int Id { get; set; }
        public int TotalCorrectAnswers { get; set; }
        public int TotalWrongAnswers { get; set; }
        public int BestScore { get; set; }
    }
}
