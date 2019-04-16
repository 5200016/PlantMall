/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysAdminDetailComponent } from 'app/entities/sys-admin/sys-admin-detail.component';
import { SysAdmin } from 'app/shared/model/sys-admin.model';

describe('Component Tests', () => {
    describe('SysAdmin Management Detail Component', () => {
        let comp: SysAdminDetailComponent;
        let fixture: ComponentFixture<SysAdminDetailComponent>;
        const route = ({ data: of({ sysAdmin: new SysAdmin(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysAdminDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysAdminDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysAdminDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysAdmin).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
