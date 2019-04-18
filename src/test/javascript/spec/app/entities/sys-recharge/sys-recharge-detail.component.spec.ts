/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysRechargeDetailComponent } from 'app/entities/sys-recharge/sys-recharge-detail.component';
import { SysRecharge } from 'app/shared/model/sys-recharge.model';

describe('Component Tests', () => {
    describe('SysRecharge Management Detail Component', () => {
        let comp: SysRechargeDetailComponent;
        let fixture: ComponentFixture<SysRechargeDetailComponent>;
        const route = ({ data: of({ sysRecharge: new SysRecharge(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysRechargeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysRechargeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysRechargeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysRecharge).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
