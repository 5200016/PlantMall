/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysMemberSettingDetailComponent } from 'app/entities/sys-member-setting/sys-member-setting-detail.component';
import { SysMemberSetting } from 'app/shared/model/sys-member-setting.model';

describe('Component Tests', () => {
    describe('SysMemberSetting Management Detail Component', () => {
        let comp: SysMemberSettingDetailComponent;
        let fixture: ComponentFixture<SysMemberSettingDetailComponent>;
        const route = ({ data: of({ sysMemberSetting: new SysMemberSetting(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysMemberSettingDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysMemberSettingDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysMemberSettingDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysMemberSetting).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
