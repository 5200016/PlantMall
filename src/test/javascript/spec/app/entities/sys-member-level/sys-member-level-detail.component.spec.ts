/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysMemberLevelDetailComponent } from 'app/entities/sys-member-level/sys-member-level-detail.component';
import { SysMemberLevel } from 'app/shared/model/sys-member-level.model';

describe('Component Tests', () => {
    describe('SysMemberLevel Management Detail Component', () => {
        let comp: SysMemberLevelDetailComponent;
        let fixture: ComponentFixture<SysMemberLevelDetailComponent>;
        const route = ({ data: of({ sysMemberLevel: new SysMemberLevel(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysMemberLevelDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysMemberLevelDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysMemberLevelDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysMemberLevel).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
