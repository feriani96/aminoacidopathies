import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICasdecesbasage, NewCasdecesbasage } from '../casdecesbasage.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICasdecesbasage for edit and NewCasdecesbasageFormGroupInput for create.
 */
type CasdecesbasageFormGroupInput = ICasdecesbasage | PartialWithRequiredKeyOf<NewCasdecesbasage>;

type CasdecesbasageFormDefaults = Pick<
  NewCasdecesbasage,
  'id' | 'confirme' | 'suspecte' | 'tbl_neuro' | 'tbl_hemorragique' | 'tbl_infx' | 'bautre_circonstance_deces' | 'np_circonstances_deces'
>;

type CasdecesbasageFormGroupContent = {
  id: FormControl<ICasdecesbasage['id'] | NewCasdecesbasage['id']>;
  confirme: FormControl<ICasdecesbasage['confirme']>;
  code_registre: FormControl<ICasdecesbasage['code_registre']>;
  suspecte: FormControl<ICasdecesbasage['suspecte']>;
  lien_de_parente: FormControl<ICasdecesbasage['lien_de_parente']>;
  autre_lien_parente: FormControl<ICasdecesbasage['autre_lien_parente']>;
  an_age_de_deces: FormControl<ICasdecesbasage['an_age_de_deces']>;
  mois_age_de_deces: FormControl<ICasdecesbasage['mois_age_de_deces']>;
  jours_age_de_deces: FormControl<ICasdecesbasage['jours_age_de_deces']>;
  tbl_neuro: FormControl<ICasdecesbasage['tbl_neuro']>;
  tbl_hemorragique: FormControl<ICasdecesbasage['tbl_hemorragique']>;
  tbl_infx: FormControl<ICasdecesbasage['tbl_infx']>;
  autre_circonstances_deces: FormControl<ICasdecesbasage['autre_circonstances_deces']>;
  bautre_circonstance_deces: FormControl<ICasdecesbasage['bautre_circonstance_deces']>;
  np_circonstances_deces: FormControl<ICasdecesbasage['np_circonstances_deces']>;
  lieu_deces: FormControl<ICasdecesbasage['lieu_deces']>;
};

export type CasdecesbasageFormGroup = FormGroup<CasdecesbasageFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CasdecesbasageFormService {
  createCasdecesbasageFormGroup(casdecesbasage: CasdecesbasageFormGroupInput = { id: null }): CasdecesbasageFormGroup {
    const casdecesbasageRawValue = {
      ...this.getFormDefaults(),
      ...casdecesbasage,
    };
    return new FormGroup<CasdecesbasageFormGroupContent>({
      id: new FormControl(
        { value: casdecesbasageRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      confirme: new FormControl(casdecesbasageRawValue.confirme),
      code_registre: new FormControl(casdecesbasageRawValue.code_registre),
      suspecte: new FormControl(casdecesbasageRawValue.suspecte),
      lien_de_parente: new FormControl(casdecesbasageRawValue.lien_de_parente),
      autre_lien_parente: new FormControl(casdecesbasageRawValue.autre_lien_parente),
      an_age_de_deces: new FormControl(casdecesbasageRawValue.an_age_de_deces),
      mois_age_de_deces: new FormControl(casdecesbasageRawValue.mois_age_de_deces),
      jours_age_de_deces: new FormControl(casdecesbasageRawValue.jours_age_de_deces),
      tbl_neuro: new FormControl(casdecesbasageRawValue.tbl_neuro),
      tbl_hemorragique: new FormControl(casdecesbasageRawValue.tbl_hemorragique),
      tbl_infx: new FormControl(casdecesbasageRawValue.tbl_infx),
      autre_circonstances_deces: new FormControl(casdecesbasageRawValue.autre_circonstances_deces),
      bautre_circonstance_deces: new FormControl(casdecesbasageRawValue.bautre_circonstance_deces),
      np_circonstances_deces: new FormControl(casdecesbasageRawValue.np_circonstances_deces),
      lieu_deces: new FormControl(casdecesbasageRawValue.lieu_deces),
    });
  }

  getCasdecesbasage(form: CasdecesbasageFormGroup): ICasdecesbasage | NewCasdecesbasage {
    return form.getRawValue() as ICasdecesbasage | NewCasdecesbasage;
  }

  resetForm(form: CasdecesbasageFormGroup, casdecesbasage: CasdecesbasageFormGroupInput): void {
    const casdecesbasageRawValue = { ...this.getFormDefaults(), ...casdecesbasage };
    form.reset(
      {
        ...casdecesbasageRawValue,
        id: { value: casdecesbasageRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CasdecesbasageFormDefaults {
    return {
      id: null,
      confirme: false,
      suspecte: false,
      tbl_neuro: false,
      tbl_hemorragique: false,
      tbl_infx: false,
      bautre_circonstance_deces: false,
      np_circonstances_deces: false,
    };
  }
}
