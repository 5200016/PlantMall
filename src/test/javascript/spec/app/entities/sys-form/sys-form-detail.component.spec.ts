/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysFormDetailComponent } from 'app/entities/sys-form/sys-form-detail.component';
import { SysForm } from 'app/shared/model/sys-form.model';

describe('Component Tests', () => {
    describe('SysForm Management Detail Component', () => {
        let comp: SysFormDetailComponent;
        let fixture: ComponentFixture<SysFormDetailComponent>;
        const route = ({ data: of({ sysForm: new SysForm(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysFormDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysFormDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysFormDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysForm).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
