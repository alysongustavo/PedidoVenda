PrimeFaces.locales['pt_BR'] = {
	messages : {
		'org.hibernate.validator.constraints.NotBlank.message' : '{0} não pode estar em branco',
		'javax.validation.constraints.NotNull.message' : '{1} é obrigatório'
	}
};

PrimeFaces.converter['com.algaworks.Categoria'] = {
		
		convert : function(element, value) {
			if (value === null || value === '') {
				return null;
			}
			
			return parseInt(value);
		}
			
};

PrimeFaces.validator.NotBlank = {
	
	MESSAGE_ID : 'org.hibernate.validator.constraints.NotBlank.message',
		
	validate : function(element, value) {
		if (value === null || value === undefined || value.trim() === '') {
			var msg = element.data('msg-notblank');
			var label = element.data('p-label');
			var context = PrimeFaces.util.ValidationContext;
			var msgObj;
			
			if (!msg) {
				msgObj = context.getMessage(this.MESSAGE_ID, label);
			} else {
				var msgObj = {
					summary : msg,
					detail : msg
				}
			}
			
			throw msgObj;
		}
	}
		
};

PrimeFaces.validator.SKU = {
	
	pattern : /^([a-zA-Z]{2}\d{4,18})?$/,
		
	validate : function(element, value) {
		if (!this.pattern.test(value)) {
			var msg = element.data('msg');
			
			if (!msg) {
				msg = 'SKU não é válido.';
			}
			
			var msgObj = {
				summary : msg,
				detail : msg
			}
			
			throw msgObj;
		}
	}
		
};

/* Corrigindo bug de versões antigas do PrimeFaces */
/*
PrimeFaces.validator.Highlighter.types.onemenu = {
		highlight : function(a) {
			a.parent().siblings(".ui-selectonemenu-trigger").addClass(
					"ui-state-error").parent().addClass(
					"ui-state-error");
			PrimeFaces.validator.Highlighter.highlightLabel(a.parent().parent().find('div input'))
		},
		unhighlight : function(a) {
			a.parent().siblings(".ui-selectonemenu-trigger")
					.removeClass("ui-state-error").parent()
					.removeClass("ui-state-error");
			PrimeFaces.validator.Highlighter.unhighlightLabel(a
					.parent().parent().find('div input'))
		}
};
*/