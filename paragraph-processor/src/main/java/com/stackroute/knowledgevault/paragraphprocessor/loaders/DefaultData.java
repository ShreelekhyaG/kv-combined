package com.stackroute.knowledgevault.paragraphprocessor.loaders;


import com.stackroute.knowledgevault.domain.ParaContent;
import com.stackroute.knowledgevault.paragraphprocessor.service.ParaContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DefaultData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ParaContentService paraContentService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
            paraContentService.saveContent(new ParaContent(100,100,"\n" +
                    "Human immunodeficiency virus infection and acquired immune deficiency syndrome (HIV/AIDS) is a spectrum of conditions caused by infection with the human immunodeficiency virus (HIV). Following initial infection, a person may not notice any symptoms or may experience a brief period of influenza-like illness. Typically, this is followed by a prolonged period with no symptoms. As the infection progresses, it interferes more with the immune system, increasing the risk of developing common infections such as tuberculosis, as well as other opportunistic infections, and tumors that rarely affect people who have working immune systems. These late symptoms of infection are referred to as acquired immunodeficiency syndrome (AIDS). This stage is often also associated with unintended weight loss."));
            paraContentService.saveContent(new ParaContent(101,101,"Malaria is a mosquito-borne infectious disease affecting humans and other animals caused by parasitic single-celled microorganisms belonging to the Plasmodium group.[2] Malaria causes symptoms that typically include fever, tiredness, vomiting, and headaches. In severe cases it can cause yellow skin, seizures, coma, or death. Symptoms usually begin ten to fifteen days after being bitten by an infected mosquito.[2] If not properly treated, people may have recurrences of the disease months later. In those who have recently survived an infection, reinfection usually causes milder symptoms.[1] This partial resistance disappears over months to years if the person has no continuing exposure to malaria. The disease is most commonly transmitted by an infected female Anopheles mosquito. The mosquito bite introduces the parasites from the mosquito's saliva into a person's blood."));
            paraContentService.saveContent(new ParaContent(102,102,"Tuberculosis (TB) is an infectious disease usually caused by Mycobacterium tuberculosis (MTB) bacteria. Tuberculosis generally affects the lungs, but can also affect other parts of the body.[1] Most infections do not have symptoms, in which case it is known as latent tuberculosis. About 10% of latent infections progress to active disease which, if left untreated, kills about half of those infected. The classic symptoms of active TB are a chronic cough with blood-containing sputum, fever, night sweats, and weight loss. It was historically called \"consumption\" due to the weight loss.Infection of other organs can cause a wide range of symptoms. Tuberculosis is spread through the air when people who have active TB in their lungs cough, spit, speak, or sneeze. People with latent TB do not spread the disease. Active infection occurs more often in people with HIV/AIDS and in those who smoke. Diagnosis of active TB is based on chest X-rays, as well as microscopic examination and culture of body fluids. Diagnosis of latent TB relies on the tuberculin skin test (TST) or blood tests."));
            paraContentService.saveContent(new ParaContent(103,103,"Diabetes mellitus type 2 (also known as type 2 diabetes) is a long-term metabolic disorder that is characterized by high blood sugar, insulin resistance, and relative lack of insulin. Common symptoms include increased thirst, frequent urination, and unexplained weight loss. Symptoms may also include increased hunger, feeling tired, and sores that do not heal. Often symptoms come on slowly.[6] Long-term complications from high blood sugar include heart disease, strokes, diabetic retinopathy which can result in blindness, kidney failure, and poor blood flow in the limbs which may lead to amputations. The sudden onset of hyperosmolar hyperglycemic state may occur; however, ketoacidosis is uncommon. Type 2 diabetes primarily occurs as a result of obesity and lack of exercise. Some people are more genetically at risk than others. Type 2 diabetes makes up about 90% of cases of diabetes, with the other 10% due primarily to diabetes mellitus type 1 and gestational diabetes."));
            paraContentService.saveContent(new ParaContent(104,104,"Dengue fever is a mosquito-borne tropical disease caused by the dengue virus. Symptoms typically begin three to fourteen days after infection. This may include a high fever, headache, vomiting, muscle and joint pains, and a characteristic skin rash. Recovery generally takes two to seven days. In a small proportion of cases, the disease develops into the life-threatening dengue hemorrhagic fever, resulting in bleeding, low levels of blood platelets and blood plasma leakage, or into dengue shock syndrome, where dangerously low blood pressure occurs. Dengue is spread by several species of mosquito of the Aedes type, principally A. aegypti. The virus has five types; infection with one type usually gives lifelong immunity to that type, but only short-term immunity to the others. Subsequent infection with a different type increases the risk of severe complications. A number of tests are available to confirm the diagnosis including detecting antibodies to the virus or its RNA."));
            paraContentService.saveContent(new ParaContent(105,105,"During a heart attack, the blood supply that normally nourishes the heart with oxygen is cut off and the heart muscle begins to die. Heart attacks — also called myocardial infarctions — are very common in the United States. In fact, it’s estimated that one happens every 40 seconds.\n" +
                    "Some people who are having a heart attack have warning signs, while others show no signs. "));
            paraContentService.saveContent(new ParaContent(106,106,"Typhoid fever, also known simply as typhoid, is a bacterial infection due to Salmonella typhi that causes symptoms. Symptoms may vary from mild to severe and usually begin six to thirty days after exposure. Often there is a gradual onset of a high fever over several days; weakness, abdominal pain, constipation, and headaches also commonly occur.Diarrhea is uncommon and vomiting is not usually severe. Some people develop a skin rash with rose colored spots. In severe cases there may be confusion. Without treatment, symptoms may last weeks or months. Other people may carry the bacterium without being affected; however, they are still able to spread the disease to others. Typhoid fever is a type of enteric fever, along with paratyphoid fever. The cause is the bacterium Salmonella Typhi, also known as Salmonella enterica serotype Typhi, growing in the intestines and blood. Typhoid is spread by eating or drinking food or water contaminated with the feces of an infected person. Risk factors include poor sanitation and poor hygiene. Those who travel in the developing world are also at risk. Only humans can be infected. Symptoms are similar to those of many other infectious diseases. Diagnosis is by either culturing the bacteria or detecting the bacterium's DNA in the blood, stool, or bone marrow. Culturing the bacterium can be difficult. Bone marrow testing is the most accurate"));
            paraContentService.saveContent(new ParaContent(107,107,"Cholera is an infection of the small intestine by some strains of the bacterium Vibrio cholerae. Symptoms may range from none, to mild, to severe. The classic symptom is large amounts of watery diarrhea that lasts a few days. Vomiting and muscle cramps may also occur. Diarrhea can be so severe that it leads within hours to severe dehydration and electrolyte imbalance.[1] This may result in sunken eyes, cold skin, decreased skin elasticity, and wrinkling of the hands and feet. Dehydration can cause the skin to turn bluish. Symptoms start two hours to five days after exposure.Cholera is caused by a number of types of Vibrio cholerae, with some types producing more severe disease than others. It is spread mostly by unsafe water and unsafe food that has been contaminated with human feces containing the bacteria. Undercooked seafood is a common source. Humans are the only animal affected. Risk factors for the disease include poor sanitation, not enough clean drinking water, and poverty. There are concerns that rising sea levels will increase rates of disease. Cholera can be diagnosed by a stool test. A rapid dipstick test is available but is not as accurate."));
         }
}

