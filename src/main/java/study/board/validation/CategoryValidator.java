package study.board.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import study.board.dto.ArticleForm;
import study.board.utils.Category;

@Component
public class CategoryValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ArticleForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ArticleForm articleForm = (ArticleForm) target;

        if(!Category.contains(articleForm.getCategory())){
            errors.rejectValue("category", "wrong.articleForm.category");
        }
    }
}
