using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Diagnostics.CodeAnalysis;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.Extensions.Options;

namespace MobilProjeBackend.Models
{
    public class Question
    {
        [Key, NotNull] 
        public long Id { get; set; }

        public string QuestionParagraph { get; set; }

        [NotMapped]
        public List<string> Options
        {
            get
            {
                if (string.IsNullOrEmpty(OptionsString))
                    return new List<string>();

                return OptionsString.Split(';').ToList();
            }
            set
            {
                OptionsString = "";
                int lastIndex = value.Count - 1;
                for (int i = 0; i < value.Count; i++)
                {
                    OptionsString += value[i];
                    if (i != lastIndex)
                        OptionsString += ";";
                }

            }
        }

        public string OptionsString { get; set; }
        public int AnswerIndex { get; set; }
    }
}
